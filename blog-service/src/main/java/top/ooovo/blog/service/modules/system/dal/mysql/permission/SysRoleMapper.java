package top.ooovo.blog.service.modules.system.dal.mysql.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRoleExportReqVO;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.SysRolePageReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysRoleDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author QAQ
 * @date 2021/11/29
 */

@Mapper
public interface SysRoleMapper extends BaseMapperX<SysRoleDO> {

    default SysRoleDO selectByName(String name) {
        return selectOne(new QueryWrapperX<SysRoleDO>().eq("name", name));
    }

    default SysRoleDO selectByCode(String code) {
        return selectOne(new QueryWrapperX<SysRoleDO>().eq("code", code));
    }

    default List<SysRoleDO> listRoles(SysRoleExportReqVO reqVO) {
        return selectList(new QueryWrapperX<SysRoleDO>().likeIfPresent("name", reqVO.getName())
                .likeIfPresent("code", reqVO.getCode())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

    default PageResult<SysRoleDO> selectPage(SysRolePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysRoleDO>().likeIfPresent("name", reqVO.getName())
                .likeIfPresent("code", reqVO.getCode())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

    default List<SysRoleDO> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(new QueryWrapperX<SysRoleDO>().in("status", statuses));
    }

    default boolean selectExistsByUpdateTimeAfter(Date maxUpdateTime) {
        return selectOne(new QueryWrapper<SysRoleDO>().select("id")
                .gt("update_time", maxUpdateTime).last("LIMIT 1")) != null;
    }
}
