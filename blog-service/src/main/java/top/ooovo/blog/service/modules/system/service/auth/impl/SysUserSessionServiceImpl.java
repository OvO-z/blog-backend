package top.ooovo.blog.service.modules.system.service.auth.impl;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import top.ooovo.blog.service.modules.system.dal.dataobject.auth.SysUserSessionDO;
import top.ooovo.blog.service.modules.system.dal.mysql.auth.SysUserSessionMapper;
import top.ooovo.blog.service.modules.system.dal.redis.auth.SysLoginUserRedisDAO;
import top.ooovo.blog.service.modules.system.service.auth.SysUserSessionService;
import top.ooovo.framework.security.config.SecurityProperties;
import top.ooovo.framework.security.core.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Date;

import static top.ooovo.framework.common.util.date.DateUtils.addTime;

/**
 * @author QAQ
 * @date 2021/12/2
 */

@Service
@Slf4j
public class SysUserSessionServiceImpl implements SysUserSessionService {

    @Resource
    private SysUserSessionMapper userSessionMapper;

    @Resource
    private SysLoginUserRedisDAO loginUserRedisDAO;

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public String createUserSession(LoginUser loginUser, String userIp, String userAgent) {
        // 生成 Session 编号
        String sessionId = generateSessionId();
        // 写入 Redis 缓存
        loginUser.setUpdateTime(new Date());
        loginUserRedisDAO.set(sessionId, loginUser);

        // 写入DB 中
        SysUserSessionDO userSession = SysUserSessionDO.builder().id(sessionId)
                .userId(loginUser.getId()).userType(loginUser.getUserType())
                .userIp(userIp).userAgent(userAgent).username(loginUser.getUsername())
                .sessionTimeout(addTime(Duration.ofMillis(getSessionTimeoutMillis())))
                .build();
        userSessionMapper.insert(userSession);
        return sessionId;
    }

    @Override
    public void refreshUserSession(String sessionId, LoginUser loginUser) {
        // 写入 Redis 缓存
        loginUser.setUpdateTime(new Date());
        loginUserRedisDAO.set(sessionId, loginUser);
        // 更新 DB 中
        SysUserSessionDO updateObj = SysUserSessionDO.builder().id(sessionId).build();
        updateObj.setUsername(loginUser.getUsername())
                .setUpdateTime(new Date());
        updateObj.setSessionTimeout(addTime(Duration.ofMillis(getSessionTimeoutMillis())));
        userSessionMapper.updateById(updateObj);
    }

    @Override
    public LoginUser getLoginUser(String sessionId) {
        return loginUserRedisDAO.get(sessionId);
    }

    @Override
    public Long getSessionTimeoutMillis() {
        return securityProperties.getSessionTimeout().toMillis();
    }

    /**
     * 生成 Session 编号，目前采用 UUID 算法
     * TODO 修改 Session 生成算法
     *
     * @return Session 编号
     */
    private static String generateSessionId() {
        return IdUtil.fastSimpleUUID();
    }

}
