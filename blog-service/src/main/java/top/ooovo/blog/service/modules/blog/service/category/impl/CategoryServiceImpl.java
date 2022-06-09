package top.ooovo.blog.service.modules.blog.service.category.impl;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryUpdateReqVO;
import top.ooovo.blog.service.modules.blog.convert.category.CategoryConvert;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.blog.service.modules.blog.dal.mysql.category.CategoryMapper;
import top.ooovo.blog.service.modules.blog.service.category.CategoryService;
import top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO;
import top.ooovo.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.*;
import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @author QAQ
 * @date 2022/6/4
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDO> listCategories() {

        return null;
    }

    @Override
    public PageResult<CategoryDO> pageCategories() {
        return null;
    }

    @Override
    public void deleteCategory(List<Long> categoryIds) {
        List<CategoryDO> categories = categoryMapper.selectBatchIds(categoryIds);
        List<Long> deleteIds = categories.stream().filter(c -> c.getCount() == 0).map(CategoryDO::getId).collect(Collectors.toList());
        categoryMapper.deleteBatchIds(deleteIds);
    }

    @Override
    public Long createCategory(CategoryCreateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(null, reqVO.getName());
        CategoryDO category = CategoryConvert.INSTANCE.convert(reqVO);

        categoryMapper.insert(category);

        return category.getId();
    }

    @Override
    public void updateCategory(CategoryUpdateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(reqVO.getId(), reqVO.getName());
        CategoryDO updateCategory = CategoryConvert.INSTANCE.convert(reqVO);
        categoryMapper.updateById(updateCategory);
    }

    /**
     * 检查修改或创建
     * @param id
     * @param name
     */
    private void checkCreateOrUpdate(Long id, String name) {
        // 校验用户存在
        this.checkCategoryExists(id);
        // 校验用户名唯一
        this.checkNameUnique(id, name);
    }

    /**
     * 检查分类是否存在
     * @param id
     */
    private void checkCategoryExists(Long id) {
        if (id == null) {
            return;
        }
        CategoryDO category = categoryMapper.selectById(id);
        if (category == null) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
    }

    /**
     * 检查分类名唯一性
     * @param id
     * @param name
     */
    private void checkNameUnique(Long id, String name) {
        if (StrUtil.isBlank(name)) {
            return;
        }
        CategoryDO category = categoryMapper.selectByName(name);
        if (category == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw exception(CATEGORY_NAME_EXISTS);
        }
        if (!category.getId().equals(id)) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
    }
}
