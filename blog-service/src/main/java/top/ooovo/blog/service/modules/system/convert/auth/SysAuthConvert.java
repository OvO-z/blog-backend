package top.ooovo.blog.service.modules.system.convert.auth;

import top.ooovo.blog.service.modules.system.controller.auth.vo.SysAuthMenuRespVO;
import top.ooovo.blog.service.modules.system.controller.auth.vo.SysAuthPermissionInfoRespVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysMenuDO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysRoleDO;
import top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO;
import top.ooovo.blog.service.modules.system.enums.permission.MenuIdEnum;
import top.ooovo.framework.common.enums.UserTypeEnum;
import top.ooovo.framework.common.util.collection.CollectionUtils;
import top.ooovo.framework.security.core.LoginUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author QAQ
 * @date 2021/11/25
 */

@Mapper
public interface SysAuthConvert {
    SysAuthConvert INSTANCE = Mappers.getMapper(SysAuthConvert.class);

    /**
     * 忽略更新时间字段
     * 字段相同，但是含义不同
     * @param bean SysUserDO
     * @return
     */
    @Mapping(source = "updateTime", target = "updateTime", ignore = true)
    LoginUser convert0(SysUserDO bean);

    default LoginUser convert(SysUserDO bean) {
        // 目的，为了设置 UserTypeEnum.ADMIN.getValue()
        return convert0(bean).setUserType(UserTypeEnum.ADMIN.getValue());
    }

    SysAuthMenuRespVO convertTreeNode(SysMenuDO menu);

    default SysAuthPermissionInfoRespVO convert(SysUserDO user, List<SysRoleDO> roleList, List<SysMenuDO> menuList) {
        return SysAuthPermissionInfoRespVO.builder()
                .user(SysAuthPermissionInfoRespVO.UserVO.builder().nickname(user.getNickname()).build())
                .roles(CollectionUtils.convertSet(roleList, SysRoleDO::getCode))
                .permissions(CollectionUtils.convertSet(menuList, SysMenuDO::getPermission))
                .build();
    }

    /**
     * 将菜单列表，构建成菜单树
     *
     * @param menuList 菜单列表
     * @return 菜单树
     */
    default List<SysAuthMenuRespVO> buildMenuTree(List<SysMenuDO> menuList) {
        // 排序，保证菜单的有序性
        menuList.sort(Comparator.comparing(SysMenuDO::getSort));
        // 构建菜单树
        // 使用 LinkedHashMap 的原因，是为了排序 。实际也可以用 Stream API ，就是太丑了。
        Map<Long, SysAuthMenuRespVO> treeNodeMap = new LinkedHashMap<>();
        menuList.forEach(menu -> treeNodeMap.put(menu.getId(), SysAuthConvert.INSTANCE.convertTreeNode(menu)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getParentId().equals(MenuIdEnum.ROOT.getId())).forEach(childNode -> {
            // 获得父节点
            SysAuthMenuRespVO parentNode = treeNodeMap.get(childNode.getParentId());
            if (parentNode == null) {
                LoggerFactory.getLogger(getClass()).error("[buildRouterTree][resource({}) 找不到父资源({})]",
                        childNode.getId(), childNode.getParentId());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return CollectionUtils.filterList(treeNodeMap.values(), node -> MenuIdEnum.ROOT.getId().equals(node.getParentId()));
    }
}
