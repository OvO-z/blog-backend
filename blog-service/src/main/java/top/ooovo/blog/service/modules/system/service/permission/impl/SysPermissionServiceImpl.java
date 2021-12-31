package top.ooovo.blog.service.modules.system.service.permission.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysMenuDO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysRoleDO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysUserRoleDO;
import top.ooovo.blog.service.modules.system.dal.mysql.permission.SysUserRoleMapper;
import top.ooovo.blog.service.modules.system.service.permission.SysPermissionService;
import top.ooovo.blog.service.modules.system.service.permission.SysRoleService;
import top.ooovo.framework.common.util.collection.CollectionUtils;
import top.ooovo.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 权限 Service 实现类
 *
 * @author QAQ
 * @date 2021/12/1
 */

@Service("ss")
@Slf4j
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Resource
    private SysRoleService roleService;

    @Override
    public List<SysMenuDO> getRoleMenusFromCache(Collection<Long> roleIds, Collection<Integer> menuTypes, Collection<Integer> menusStatuses) {
        //TODO:QAQ添加角色菜单映射缓存
        return null;
    }

    @Override
    public Set<Long> getUserRoleIds(Long userId, Collection<Integer> roleStatuses) {
        List<SysUserRoleDO> userRoleList = userRoleMapper.selectListByUserId(userId);

        if (CollectionUtil.isNotEmpty(roleStatuses)) {
            userRoleList.removeIf(userRoleDO -> {
                SysRoleDO role = roleService.getRoleFromCache(userRoleDO.getRoleId());
                return role == null || !roleStatuses.contains(role.getStatus());
            });
        }
        return CollectionUtils.convertSet(userRoleList, SysUserRoleDO::getRoleId);
    }

    @Override
    public void assignUserRole(Long userId, Set<Long> roleIds) {
        // 获得角色拥有角色编号
        Set<Long> dbRoleIds = CollectionUtils.convertSet(userRoleMapper.selectListByUserId(userId),
                SysUserRoleDO::getRoleId);
        // 计算新增和删除的角色编号
        Collection<Long> createRoleIds = CollUtil.subtract(roleIds, dbRoleIds);
        Collection<Long> deleteMenuIds = CollUtil.subtract(dbRoleIds, roleIds);

        // 执行新增和删除。对于已经授权的角色，不用做任何处理
        if (!CollectionUtil.isEmpty(createRoleIds)) {
            userRoleMapper.insertList(userId, createRoleIds);
        }
        if (!CollectionUtil.isEmpty(deleteMenuIds)) {
            userRoleMapper.deleteListByUserIdAndRoleIdIds(userId, deleteMenuIds);
        }
    }

    @Override
    public boolean hasPermission(String permission) {
        return hasAnyPermissions(permission);
    }

    @Override
    public boolean hasAnyPermissions(String... permissions) {
        // 如果为空，说明已经有权限
        if (ArrayUtil.isEmpty(permissions)) {
            return true;
        }

        // 获得当前登录的角色。如果为空，说明没有权限
        Set<Long> roleIds = SecurityFrameworkUtils.getLoginUserRoleIds();
        if (CollUtil.isEmpty(roleIds)) {
            return false;
        }
        // 判断是否是超管。如果是，当然符合条件
        if (roleService.hasAnyAdmin(roleIds)) {
            return true;
        }
        // TODO
        return false;
    }

    @Override
    public boolean hasRole(String role) {
        // TODO @QAQ：是否有角色
        return false;
    }

    @Override
    public boolean hasAnyRoles(String... roles) {
        // TODO @QAQ：是否有任一角色
        return false;
    }

    @Override
    public void processUserDeleted(Long userId) {
        userRoleMapper.deleteListByUserId(userId);
    }
}
