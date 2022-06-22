package top.ooovo.blog.service.modules.blog.convert.article;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleRespVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleUpdateReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.article.ArticleDO;
import top.ooovo.framework.common.pojo.PageResult;


/**
 * @author QAQ
 * @date 2022/6/12
 */
@Mapper
public interface ArticleConvert {

    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    ArticleDO convert(ArticleCreateReqVO reqVO);

    ArticleDO convert(ArticleUpdateReqVO reqVO);

    ArticleRespVO convert(ArticleDO articleDO);

    PageResult<ArticleRespVO> convertPage(PageResult<ArticleDO> page);
}
