package top.ooovo.blog.service.modules.system.convert.logger;

import top.ooovo.blog.service.modules.system.controller.logger.vo.SysLoginLogRespVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.logger.SysLoginLogDO;
import top.ooovo.blog.service.modules.system.service.logger.dto.SysLoginLogCreateReqDTO;
import top.ooovo.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author QAQ
 * @date 2021/12/1
 */
@Mapper
public interface SysLoginLogConvert {
    SysLoginLogConvert INSTANCE = Mappers.getMapper(SysLoginLogConvert.class);

    SysLoginLogDO convert(SysLoginLogCreateReqDTO bean);

    PageResult<SysLoginLogRespVO> convertPage(PageResult<SysLoginLogDO> page);
}
