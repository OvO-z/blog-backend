package top.ooovo.blog.service.modules.blog.service.article;

import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticlePageReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleUpdateReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.article.ArticleDO;
import top.ooovo.framework.common.pojo.PageResult;

import javax.validation.Valid;

/**
 * 博客文章 Service 接口
 * @author QAQ
 * @date 2022/5/6
 */
public interface ArticleService {

    /**
     * 获得文章
     * @param id 文章id
     * @return 文章
     */
    ArticleDO getArticle(Long id);

    /**
     * 获得文章分页列表
     * @return
     */
    PageResult<ArticleDO> getArticlePage(@Valid ArticlePageReqVO reqVO);

    /**
     * 删除文章
     * @param id
     */
    void deleteArticle(Long id);

    /**
     * 创建文章
     * @param reqVO 创建信息
     * @return 文章编号
     */
    Long createArticle(@Valid ArticleCreateReqVO reqVO);

    /**
     * 修改文章
     * @param reqVO
     */
    void updateArticle(@Valid ArticleUpdateReqVO reqVO);
}
