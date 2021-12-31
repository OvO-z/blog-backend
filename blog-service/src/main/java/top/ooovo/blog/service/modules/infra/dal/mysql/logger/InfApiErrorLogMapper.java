package top.ooovo.blog.service.modules.infra.dal.mysql.logger;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

/**
 * @author QAQ
 * @date 2021/11/25
 */

@Mapper
public interface InfApiErrorLogMapper extends BaseMapperX<InfApiErrorLogDO> {

    default PageResult<InfApiErrorLogDO> selectPage(InfApiErrorLogPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<InfApiErrorLogDO>()
                .eqIfPresent("user_id", reqVO.getUserId())
                .eqIfPresent("user_type", reqVO.getUserType())
                .eqIfPresent("application_name", reqVO.getApplicationName())
                .likeIfPresent("request_url", reqVO.getRequestUrl())
                .betweenIfPresent("exception_time", reqVO.getBeginExceptionTime(), reqVO.getEndExceptionTime())
                .eqIfPresent("process_status", reqVO.getProcessStatus())
                .orderByDesc("id")
        );
    }
}
