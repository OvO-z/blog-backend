package top.ooovo.blog.service.modules.blog.service.category.impl;

import org.springframework.stereotype.Service;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.blog.service.modules.blog.service.category.CategoryService;
import top.ooovo.framework.common.pojo.PageResult;

import java.util.List;

/**
 * @author QAQ
 * @date 2022/6/4
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public PageResult<CategoryDO> listCategories() {
        // TODO list Category
        return null;
    }

    @Override
    public void deleteCategory(List<Long> categoryIds) {
        // TODO deleteCategory
    }

    @Override
    public void createCategory() {

    }

    @Override
    public void updateCategory(Long id) {

    }
}
