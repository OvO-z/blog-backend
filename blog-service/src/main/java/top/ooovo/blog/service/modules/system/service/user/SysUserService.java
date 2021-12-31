package top.ooovo.blog.service.modules.system.service.user;

import cn.hutool.core.collection.CollUtil;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserPageReqVO;
import top.ooovo.blog.service.modules.system.controller.user.vo.SysUserUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.common.util.collection.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 后台用户 Service 接口
 *
 * @author QAQ
 * @date 2021/11/23
 */

public interface SysUserService {

    /**
     * 创建用户
     *
     * @param reqVO 用户信息
     * @return 用户编号
     */
    Long createUser(SysUserCreateReqVO reqVO);


    /**
     * 修改用户
     *
     * @param reqVO 用户信息
     */
    void updateUser(SysUserUpdateReqVO reqVO);

    /**
     * 删除用户
     *
     * @param id 用户编号
     */
    void deleteUser(Long id);


    /**
     * 更新用户的最后登陆信息
     *
     * @param id 用户编号
     * @param loginIp 登陆 IP
     */
    void updateUserLogin(Long id, String loginIp);


    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    SysUserDO getUser(Long id);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    SysUserDO getUserByUsername(String username);

    /**
     * 修改状态
     *
     * @param id     用户编号
     * @param status 状态
     */
    void updateUserStatus(Long id, Integer status);


    /**
     * 获得用户列表，基于昵称模糊匹配
     *
     * @param nickname 昵称
     * @return 用户列表
     */
    List<SysUserDO> getUsersByNickname(String nickname);

    /**
     * 获得用户列表，基于用户账号模糊匹配
     *
     * @param username 用户账号
     * @return 用户列表
     */
    List<SysUserDO> getUsersByUsername(String username);

    /**
     * 获得用户分页列表
     *
     * @param reqVO 分页条件
     * @return 分页列表
     */
    PageResult<SysUserDO> getUserPage(SysUserPageReqVO reqVO);


    /**
     * 获得用户列表
     *
     * @param ids 用户编号数组
     * @return 用户列表
     */
    List<SysUserDO> getUsers(Collection<Long> ids);



    /**
     * 获得用户 Map
     *
     * @param ids 用户编号数组
     * @return 用户 Map
     */
    default Map<Long, SysUserDO> getUserMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return new HashMap<>();
        }
        return CollectionUtils.convertMap(getUsers(ids), SysUserDO::getId);
    }
}
