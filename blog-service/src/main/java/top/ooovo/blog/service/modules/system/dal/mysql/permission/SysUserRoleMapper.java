package top.ooovo.blog.service.modules.system.dal.mysql.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysUserRoleDO;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author QAQ
 * @date 2021/12/1
 */

@Mapper
public interface SysUserRoleMapper extends BaseMapperX<SysUserRoleDO> {

    default List<SysUserRoleDO> selectListByUserId(Long userId) {
        return selectList(new QueryWrapper<SysUserRoleDO>().eq("user_id", userId));
    }

    default void insertList(Long userId, Collection<Long> roleIds) {
        List<SysUserRoleDO> list = roleIds.stream().map(roleId -> {
            SysUserRoleDO entity = new SysUserRoleDO();
            entity.setUserId(userId);
            entity.setRoleId(roleId);
            return entity;
        }).collect(Collectors.toList());
        // TODO mybatis plus 增加批量插入的功能？
        list.forEach(this::insert);
    }

    default void deleteListByUserIdAndRoleIdIds(Long userId, Collection<Long> roleIds) {
        delete(new QueryWrapper<SysUserRoleDO>().eq("user_id", userId)
                .in("role_id", roleIds));
    }

    default void deleteListByUserId(Long userId) {
        delete(new QueryWrapper<SysUserRoleDO>().eq("user_id", userId));
    }
}
