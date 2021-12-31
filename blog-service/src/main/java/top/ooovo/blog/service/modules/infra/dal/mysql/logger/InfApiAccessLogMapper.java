package top.ooovo.blog.service.modules.infra.dal.mysql.logger;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogPageReqVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

/**
 * @author QAQ
 * @date 2021/11/25
 */

@Mapper
public interface InfApiAccessLogMapper extends BaseMapperX<InfApiAccessLogDO> {

    default PageResult<InfApiAccessLogDO> selectPage(InfApiAccessLogPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<InfApiAccessLogDO>()
                .eqIfPresent("user_id", reqVO.getUserId())
                .eqIfPresent("user_type", reqVO.getUserType())
                .eqIfPresent("application_name", reqVO.getApplicationName())
                .likeIfPresent("request_url", reqVO.getRequestUrl())
                .betweenIfPresent("begin_time", reqVO.getBeginBeginTime(), reqVO.getEndBeginTime())
                .geIfPresent("duration", reqVO.getDuration())
                .eqIfPresent("result_code", reqVO.getResultCode())
                .orderByDesc("id")
        );
    }
}
