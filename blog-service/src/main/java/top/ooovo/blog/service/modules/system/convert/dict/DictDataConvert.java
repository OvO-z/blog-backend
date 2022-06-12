package top.ooovo.blog.service.modules.system.convert.dict;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataRespVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataSimpleRespVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.data.DictDataUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.dict.DictDataDO;
import top.ooovo.blog.service.modules.system.service.dict.dto.DictDataRespDTO;
import top.ooovo.framework.common.pojo.PageResult;

import java.util.Collection;
import java.util.List;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@Mapper
public interface DictDataConvert {
    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    List<DictDataSimpleRespVO> convertList(List<DictDataDO> list);

    DictDataRespVO convert(DictDataDO bean);

    PageResult<DictDataRespVO> convertPage(PageResult<DictDataDO> page);

    DictDataDO convert(DictDataUpdateReqVO bean);

    DictDataDO convert(DictDataCreateReqVO bean);


    DictDataRespDTO convert02(DictDataDO bean);

    List<DictDataRespDTO> convertList03(Collection<DictDataDO> list);
}
