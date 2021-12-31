package top.ooovo.blog.service.modules.system.service.auth.impl;

import lombok.extern.slf4j.Slf4j;
import top.ooovo.blog.service.modules.system.controller.auth.vo.SysAuthLoginReqVO;
import top.ooovo.blog.service.modules.system.convert.auth.SysAuthConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO;
import top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants;
import top.ooovo.blog.service.modules.system.enums.logger.SysLoginLogTypeEnum;
import top.ooovo.blog.service.modules.system.enums.logger.SysLoginResultEnum;
import top.ooovo.blog.service.modules.system.service.auth.SysAuthService;
import top.ooovo.blog.service.modules.system.service.auth.SysUserSessionService;
import top.ooovo.blog.service.modules.system.service.logger.SysLoginLogService;
import top.ooovo.blog.service.modules.system.service.logger.dto.SysLoginLogCreateReqDTO;
import top.ooovo.blog.service.modules.system.service.permission.SysPermissionService;
import top.ooovo.blog.service.modules.system.service.user.SysUserService;
import top.ooovo.framework.common.enums.CommonStatusEnum;
import top.ooovo.framework.common.enums.UserTypeEnum;
import top.ooovo.framework.common.util.servlet.ServletUtils;
import top.ooovo.framework.security.core.LoginUser;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.singleton;
import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.*;
import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * Auth Service 实现类
 *
 * @author QAQ
 * @date 2021/11/25
 */

@Service
@Slf4j
public class SysAuthServiceImpl implements SysAuthService {

    @Resource
    private SysLoginLogService loginLogService;

    @Resource
    @Lazy
    /**
     * 延迟加载，因为存在相互依赖的问题
     */
    private AuthenticationManager authenticationManager;

    @Resource
    private SysUserService userService;

    @Resource
    private SysPermissionService permissionService;

    @Resource
    private SysUserSessionService userSessionService;

    @Override
    public String login(@Valid SysAuthLoginReqVO reqVO, String userIp, String userAgent) {
        // 使用账号密码，进行登录。
        LoginUser loginUser = this.login0(reqVO.getUsername(), reqVO.getPassword());
        // 获取用户角色列表
        loginUser.setRoleIds(this.getUserRoleIds(loginUser.getId()));
        // 缓存登陆用户到 Redis 中，返回 sessionId 编号
        return userSessionService.createUserSession(loginUser, userIp, userAgent);
    }

    @Override
    public LoginUser verifyTokenAndRefresh(String token) {
        // 获得 LoginUser
        LoginUser loginUser = userSessionService.getLoginUser(token);
        if (loginUser == null) {
            return null;
        }
        // 刷新 LoginUser 缓存
        this.refreshLoginUserCache(token, loginUser);
        return loginUser;
    }

    private void refreshLoginUserCache(String token, LoginUser loginUser) {
        // 每 1/3 的 Session 超时时间，刷新 LoginUser 缓存
        if (System.currentTimeMillis() - loginUser.getUpdateTime().getTime() <
                userSessionService.getSessionTimeoutMillis() / 3) {
            return;
        }

        // 重新加载 SysUserDO 信息
        SysUserDO user = userService.getUser(loginUser.getId());
        if (user == null || CommonStatusEnum.DISABLE.getStatus().equals(user.getStatus())) {
            // 校验 token 时，用户被禁用的情况下，也认为 token 过期，方便前端跳转到登录界面
            throw exception(AUTH_TOKEN_EXPIRED);
        }

        // 刷新 LoginUser 缓存
        loginUser.setRoleIds(this.getUserRoleIds(loginUser.getId()));
        userSessionService.refreshUserSession(token, loginUser);
    }

    @Override
    public LoginUser mockLogin(Long userId) {
        // 获取用户编号对应的 SysUserDO
        SysUserDO user = userService.getUser(userId);
        if (user == null) {
            throw new UsernameNotFoundException(String.valueOf(userId));
        }
        this.createLoginLog(user.getUsername(), SysLoginLogTypeEnum.LOGIN_MOCK, SysLoginResultEnum.SUCCESS);

        // 创建 LoginUser 对象
        LoginUser loginUser = SysAuthConvert.INSTANCE.convert(user);
        // 获取用角色列表
        loginUser.setRoleIds(this.getUserRoleIds(loginUser.getId()));
        return loginUser;
    }

    @Override
    public void logout(String token) {
        // TODO @QAQ: 登出
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDO user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // 创建LoginUser对象
        LoginUser loginUser = SysAuthConvert.INSTANCE.convert(user);
        return loginUser;
    }

    private LoginUser login0(String username, String password) {
        final SysLoginLogTypeEnum logTypeEnum = SysLoginLogTypeEnum.LOGIN_USERNAME;
        // 用户验证
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException badCredentialsException) {
            this.createLoginLog(username, logTypeEnum, SysLoginResultEnum.BAD_CREDENTIALS);
            throw exception(SysErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS);
        }catch (DisabledException disabledException) {
            this.createLoginLog(username, logTypeEnum, SysLoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        } catch (AuthenticationException authenticationException) {
            log.error("[login0][username({}) 发生未知异常]", username, authenticationException);
            this.createLoginLog(username, logTypeEnum, SysLoginResultEnum.UNKNOWN_ERROR);
            throw exception(AUTH_LOGIN_FAIL_UNKNOWN);
        }
        this.createLoginLog(username, logTypeEnum, SysLoginResultEnum.SUCCESS);
        return (LoginUser) authentication.getPrincipal();
    }

    /**
     * 获得 User 拥有的角色编号数组
     *
     * @param userId 用户编号
     * @return 角色编号数组
     */
    private Set<Long> getUserRoleIds(Long userId) {
        return permissionService.getUserRoleIds(userId, singleton(CommonStatusEnum.ENABLE.getStatus()));
    }

    private void createLoginLog(String username, SysLoginLogTypeEnum logType, SysLoginResultEnum loginResult) {
        // 获得用户
        SysUserDO user = userService.getUserByUsername(username);
        // 插入登录日志
        SysLoginLogCreateReqDTO reqDTO = new SysLoginLogCreateReqDTO();
        if (user != null) {
            reqDTO.setUserId(user.getId());
        }
        reqDTO.setUserType(UserTypeEnum.ADMIN.getValue())
                .setLogType(logType.getType())
                .setUsername(username)
                .setUserAgent(ServletUtils.getUserAgent())
                .setUserIp(ServletUtils.getClientIP())
                .setResult(loginResult.getResult());

        loginLogService.createLoginLog(reqDTO);

        // 更新最后登录时间
        if (user != null && Objects.equals(SysLoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(user.getId(), ServletUtils.getClientIP());
        }
    }
}
