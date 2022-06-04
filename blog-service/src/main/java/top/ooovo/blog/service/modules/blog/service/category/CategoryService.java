package top.ooovo.blog.service.modules.blog.service.category;

import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 目录分类 Service
 * @author QAQ
 * @date 2022/6/3
 */
public interface CategoryService {

    /**
     * 查询分类列表
     * @return 分类列表
     */
    PageResult<CategoryDO> listCategories();

    /**
     * 删除分类
     *
     * @param categoryIdList 分类id集合
     */
    void deleteCategory(List<Long> categoryIds);


    /**
     * 创建分类
     */
    void createCategory();

    /**
     * 修改分类
     * @param id 分类ID
     */
    void updateCategory(Long id);
}
