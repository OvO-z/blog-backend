package top.ooovo.blog.service.modules.blog.dal.mysql.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryCreateReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

/**
 * @author QAQ
 * @date 2022/6/8
 */

public interface CategoryMapper extends BaseMapperX<CategoryDO> {
    default CategoryDO selectByName(String name) {
        return selectOne(new QueryWrapperX<CategoryDO>().eq("name", name));
    }
}
