package top.ooovo.blog.service.modules.blog.convert.category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryUpdateReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;

/**
 * @author QAQ
 * @date 2022/6/8
 */

@Mapper
public interface CategoryConvert {

    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    CategoryDO convert(CategoryCreateReqVO bean);

    CategoryDO convert(CategoryUpdateReqVO bean);
}
