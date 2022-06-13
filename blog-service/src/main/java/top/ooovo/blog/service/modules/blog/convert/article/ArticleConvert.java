package top.ooovo.blog.service.modules.blog.convert.article;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleUpdateReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.article.ArticleDO;


/**
 * @author QAQ
 * @date 2022/6/12
 */
@Mapper
public interface ArticleConvert {

    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    ArticleDO convert(ArticleCreateReqVO reqVO);

    ArticleDO convert(ArticleUpdateReqVO reqVO);
}
