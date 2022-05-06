package top.ooovo.blog.service.modules.infra.dal.mysql.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigExportReqVO;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigPageReqVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.config.InfConfigDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

import java.util.List;

/**
 * @author QAQ
 * @date 2022/5/6
 */

@Mapper
public interface InfConfigMapper extends BaseMapperX<InfConfigDO> {

    default InfConfigDO selectByKey(String key) {
        return selectOne(new QueryWrapper<InfConfigDO>().eq("`key`", key));
    }

    default PageResult<InfConfigDO> selectPage(InfConfigPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<InfConfigDO>()
                .likeIfPresent("name", reqVO.getName())
                .likeIfPresent("`key`", reqVO.getKey())
                .eqIfPresent("`type`", reqVO.getType())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

    default List<InfConfigDO> selectList(InfConfigExportReqVO reqVO) {
        return selectList(new QueryWrapperX<InfConfigDO>()
                .likeIfPresent("name", reqVO.getName())
                .likeIfPresent("`key`", reqVO.getKey())
                .eqIfPresent("`type`", reqVO.getType())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }
}
