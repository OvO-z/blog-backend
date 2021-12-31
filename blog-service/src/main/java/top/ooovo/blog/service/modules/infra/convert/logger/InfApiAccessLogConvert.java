package top.ooovo.blog.service.modules.infra.convert.logger;

import top.ooovo.blog.service.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogRespVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import top.ooovo.framework.apilog.core.service.dto.ApiAccessLogCreateReqDTO;
import top.ooovo.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author QAQ
 * @date 2021/11/25
 */
@Mapper
public interface InfApiAccessLogConvert {
    InfApiAccessLogConvert INSTANCE = Mappers.getMapper(InfApiAccessLogConvert.class);

    InfApiAccessLogDO convert(ApiAccessLogCreateReqDTO bean);

    PageResult<InfApiAccessLogRespVO> convertPage(PageResult<InfApiAccessLogDO> page);
}
