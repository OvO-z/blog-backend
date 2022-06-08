package top.ooovo.blog.service.modules.blog.service.category.impl;

import org.springframework.stereotype.Service;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryUpdateReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.blog.service.modules.blog.dal.mysql.category.CategoryMapper;
import top.ooovo.blog.service.modules.blog.service.category.CategoryService;
import top.ooovo.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author QAQ
 * @date 2022/6/4
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public PageResult<CategoryDO> listCategories() {
        // TODO list Category
        return null;
    }

    @Override
    public void deleteCategory(List<Long> categoryIds) {
        List<CategoryDO> categories = categoryMapper.selectBatchIds(categoryIds);
        List<Long> deleteIds = categories.stream().filter(c -> c.getCount() == 0).map(CategoryDO::getId).collect(Collectors.toList());
        categoryMapper.deleteBatchIds(deleteIds);
    }

    @Override
    public void createCategory(CategoryCreateReqVO reqVO) {
//        CategoryDO existCategory =
    }

    @Override
    public void updateCategory(CategoryUpdateReqVO reqVO) {

    }
}
