package top.ooovo.blog.service.modules.system.dal.mysql.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.system.controller.permission.vo.menu.SysMenuListReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysMenuDO;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

import java.util.Date;
import java.util.List;

/**
 * @author QAQ
 * @date 2021/12/2
 */

@Mapper
public interface SysMenuMapper extends BaseMapperX<SysMenuDO> {
    default SysMenuDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new QueryWrapperX<SysMenuDO>().eq("parent_id", parentId)
                .eq("name", name));
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(new QueryWrapperX<SysMenuDO>().eq("parent_id", parentId));
    }

    default List<SysMenuDO> selectList(SysMenuListReqVO reqVO) {
        return selectList(new QueryWrapperX<SysMenuDO>().likeIfPresent("name", reqVO.getName())
                .eqIfPresent("status", reqVO.getStatus()));
    }

    default boolean selectExistsByUpdateTimeAfter(Date maxUpdateTime) {
        return selectOne(new QueryWrapper<SysMenuDO>().select("id")
                .gt("update_time", maxUpdateTime).last("LIMIT 1")) != null;
    }

}
