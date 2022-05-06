package top.ooovo.blog.service.modules.infra.convert.config;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigCreateReqVO;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigRespVO;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigUpdateReqVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.config.InfConfigDO;
import top.ooovo.framework.common.pojo.PageResult;

import java.util.List;

/**
 * @author QAQ
 * @date 2022/5/6
 */

@Mapper
public interface InfConfigConvert {

    InfConfigConvert INSTANCE = Mappers.getMapper(InfConfigConvert.class);

    PageResult<InfConfigRespVO> convertPage(PageResult<InfConfigDO> page);

    InfConfigRespVO convert(InfConfigDO bean);

    InfConfigDO convert(InfConfigCreateReqVO bean);

    InfConfigDO convert(InfConfigUpdateReqVO bean);
}
