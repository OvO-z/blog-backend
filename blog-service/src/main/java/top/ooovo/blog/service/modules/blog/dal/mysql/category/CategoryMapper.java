package top.ooovo.blog.service.modules.blog.dal.mysql.category;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.blog.controller.category.vo.CategoryPageReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

/**
 * @author QAQ
 * @date 2022/6/8
 */

@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {
    default CategoryDO selectByName(String name) {
        return selectOne(new QueryWrapperX<CategoryDO>().eq("name", name));
    }

    default PageResult<CategoryDO> selectPage(CategoryPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<CategoryDO>()
                .likeIfPresent("name", reqVO.getName())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }
}
