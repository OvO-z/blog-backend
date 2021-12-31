package top.ooovo.blog.service.modules.system.service.user.impl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserPageReqVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserUpdateReqVO;
import top.ooovo.blog.service.modules.system.convert.user.SysUserConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO;
import top.ooovo.blog.service.modules.system.dal.mysql.user.SysUserMapper;
import top.ooovo.blog.service.modules.system.service.permission.SysPermissionService;
import top.ooovo.blog.service.modules.system.service.user.SysUserService;
import top.ooovo.framework.common.enums.CommonStatusEnum;
import top.ooovo.framework.common.pojo.PageResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.USER_NOT_EXISTS;
import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.USER_USERNAME_EXISTS;
import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 用户 Service 实现类
 * @author QAQ
 * @date 2021/11/23
 */

@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SysPermissionService permissionService;

    @Override
    public Long createUser(SysUserCreateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(null, reqVO.getUsername());
        // 插入用户
        SysUserDO user = SysUserConvert.INSTANCE.convert(reqVO);
        // 默认开启
        user.setStatus(CommonStatusEnum.ENABLE.getStatus());
        // 加密密码
        user.setPassword(passwordEncoder.encode(reqVO.getPassword()));
        userMapper.insert(user);

        return user.getId();
    }

    @Override
    public void updateUser(SysUserUpdateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(reqVO.getId(), reqVO.getUsername());
        // 更新用户
        SysUserDO updateObj = SysUserConvert.INSTANCE.convert(reqVO);
        userMapper.updateById(updateObj);
    }

    /**
     * TODO
     *
     * @param id 用户编号
     * @return: void
     * @author: currylin
     * @date: 2021/12/3 16:34
     */
    @Override
    public void deleteUser(Long id) {
        // TODO @currylin：删除用户实现
        // 检验用户是否存在
        this.checkUserExists(id);
        // 删除用户
        userMapper.deleteById(id);
        //删除用户关联的数据
        permissionService.processUserDeleted(id);
    }

    @Override
    public void updateUserLogin(Long id, String loginIp) {
        // TODO @QAQ: 直接用new Date()？
        userMapper.updateById(new SysUserDO().setId(id).setLoginIp(loginIp).setLoginDate(new Date()));
    }

    @Override
    public SysUserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public SysUserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * @Description: 修改用户状态
     *
     * @param id 用户编号
     * @param status 用户状态
     * @return:
     * @author: currylin
     * @date: 2021/12/3 16:58
     */
    @Override
    public void updateUserStatus(Long id, Integer status) {
        //检查用户存在
        this.checkUserExists(id);
        //修改用户状态
        SysUserDO updateObj = new SysUserDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        userMapper.updateById(updateObj);
    }

    /**
     * 通过用户昵称查询用户
     *
     * @param nickname 用户昵称
     * @return: java.util.List<top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO>
     * @author: currylin
     * @date: 2021/12/3 17:14
     */
    @Override
    public List<SysUserDO> getUsersByNickname(String nickname) {
        return userMapper.selectListByNickname(nickname);
    }

    /**
     * 通过账号查询用户
     *
     * @param username 用户账号
     * @return: java.util.List<top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO>
     * @author: currylin
     * @date: 2021/12/3 17:19
     */
    @Override
    public List<SysUserDO> getUsersByUsername(String username) {
        return userMapper.selectListByUsername(username);
    }

    /**
     * @Description: 获取用户分页数据
     *
     * @param reqVO
     * @return: 用户账号，状态，开始时间，结束时间
     * @author: currylin
     * @date: 2021/12/3 17:25
     */
    @Override
    public PageResult<SysUserDO> getUserPage(SysUserPageReqVO reqVO) {
       return userMapper.selectPage(reqVO);
    }

    /**
     * @Description: 获取用户列表
     *
     * @param ids
     * @return: 用户账号，状态，开始时间，结束时间
     * @author: currylin
     * @date: 2021/12/3 19:32
     */
    @Override
    public List<SysUserDO> getUsers(Collection<Long> ids) {
        return userMapper.selectBatchIds(ids);
    }

    /**
     * 检验修改或创建
     * @param id 用户ID
     * @param username 用户名
     */
    private void checkCreateOrUpdate(Long id, String username) {
        // 校验用户存在
        this.checkUserExists(id);
        // 校验用户名唯一
        this.checkUsernameUnique(id, username);
    }

    /**
     * @Description: 检查用户是否存在
     *
     * @param id 用户编号
     * @return:
     * @author: currylin
     * @date: 2021/12/3 16:39
     */
    public void checkUserExists(Long id) {
        if (id == null) {
            return;
        }
        SysUserDO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    public void checkUsernameUnique(Long id, String username) {
        if (StrUtil.isBlank(username)) {
            return;
        }
        SysUserDO user = userMapper.selectByUsername(username);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw exception(USER_USERNAME_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw exception(USER_USERNAME_EXISTS);
        }
    }
}
