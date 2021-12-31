package top.ooovo.blog.service.modules.system.service.permission;

import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysMenuDO;
import top.ooovo.framework.security.core.service.SecurityPermissionFrameworkService;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 权限 Service 接口
 *
 * 提供用户-角色、角色-菜单、角色-部门的关联权限处理
 * @author QAQ
 * @date 2021/12/1
 */

public interface SysPermissionService extends SecurityPermissionFrameworkService {

    /**
     * 获得角色们拥有的菜单列表，从缓存中获取
     *
     * 任一参数为空时，则返回为空
     *
     * @param roleIds 角色编号数组
     * @param menuTypes 菜单类型数组
     * @param menusStatuses 菜单状态数组
     * @return 菜单列表
     */
    List<SysMenuDO> getRoleMenusFromCache(Collection<Long> roleIds, Collection<Integer> menuTypes,
                                          Collection<Integer> menusStatuses);

    /**
     * 获得用户拥有的角色编号集合
     *
     * @param userId 用户编号
     * @param roleStatuses 角色状态集合. 允许为空，为空时不过滤
     * @return 角色编号集合
     */
    Set<Long> getUserRoleIds(Long userId, @Nullable Collection<Integer> roleStatuses);


    /**
     * 设置用户角色
     *
     * @param userId 角色编号
     * @param roleIds 角色编号集合
     */
    void assignUserRole(Long userId, Set<Long> roleIds);

    /**
     * @Description: 删除用户时，删除其关联的授权数据
     *
     * @param userId 用户编号
     * @return:
     * @author: currylin
     * @date: 2021/12/3 16:50
     */
    void processUserDeleted(Long userId);
}
