package top.ooovo.blog.service.modules.blog.dal.mysql.article;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticlePageReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.article.ArticleDO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

/**
 * @author QAQ
 * @date 2022/5/6
 */

@Mapper
public interface ArticleMapper extends BaseMapperX<ArticleDO> {

    default ArticleDO selectByCode(String code) {
        return selectOne(new QueryWrapperX<ArticleDO>().eq("code", code));
    }

    default PageResult<ArticleDO> selectPage(ArticlePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<ArticleDO>()
                .likeIfPresent("code", reqVO.getCode())
                .likeIfPresent("title", reqVO.getTitle())
                .eqIfPresent("category_id", reqVO.getCategoryId())
                .eqIfPresent("`type`", reqVO.getType())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

    default ArticleDO selectByTitle(String title) {
        return selectOne(new QueryWrapperX<ArticleDO>().eq("title", title));
    }

}
