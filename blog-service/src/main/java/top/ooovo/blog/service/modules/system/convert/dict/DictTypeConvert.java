package top.ooovo.blog.service.modules.system.convert.dict;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ooovo.blog.service.modules.system.controller.dict.vo.type.DictTypeCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.type.DictTypeRespVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.type.DictTypeSimpleRespVO;
import top.ooovo.blog.service.modules.system.controller.dict.vo.type.DictTypeUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.dict.DictTypeDO;
import top.ooovo.framework.common.pojo.PageResult;

import java.util.List;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@Mapper
public interface DictTypeConvert {

    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    PageResult<DictTypeRespVO> convertPage(PageResult<DictTypeDO> bean);

    DictTypeRespVO convert(DictTypeDO bean);

    DictTypeDO convert(DictTypeCreateReqVO bean);

    DictTypeDO convert(DictTypeUpdateReqVO bean);

    List<DictTypeSimpleRespVO> convertList(List<DictTypeDO> list);

}
