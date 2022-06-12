package top.ooovo.blog.service.modules.blog.convert.category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryRespVO;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryUpdateReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.framework.common.pojo.PageResult;

import java.util.List;

/**
 * @author QAQ
 * @date 2022/6/8
 */

@Mapper
public interface CategoryConvert {

    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    CategoryDO convert(CategoryCreateReqVO bean);

    CategoryDO convert(CategoryUpdateReqVO bean);

    PageResult<CategoryRespVO> convertPage(PageResult<CategoryDO> page);

    List<CategoryRespVO> convertList(List<CategoryDO> list);
}
