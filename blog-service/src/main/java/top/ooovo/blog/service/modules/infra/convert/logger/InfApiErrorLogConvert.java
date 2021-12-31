package top.ooovo.blog.service.modules.infra.convert.logger;

import top.ooovo.blog.service.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogRespVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import top.ooovo.framework.apilog.core.service.dto.ApiErrorLogCreateReqDTO;
import top.ooovo.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * API 错误日志 Convert
 *
 * @author QAQ
 * @date 2021/11/25
 */

@Mapper
public interface InfApiErrorLogConvert {
    InfApiErrorLogConvert INSTANCE = Mappers.getMapper(InfApiErrorLogConvert.class);

    InfApiErrorLogDO convert(ApiErrorLogCreateReqDTO bean);

    PageResult<InfApiErrorLogRespVO> convertPage(PageResult<InfApiErrorLogDO> page);
}
