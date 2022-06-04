package top.ooovo.blog.service.modules.blog.service.article.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticlePageReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleUpdateReqVO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.article.ArticleDO;
import top.ooovo.blog.service.modules.blog.dal.mysql.article.ArticleMapper;
import top.ooovo.blog.service.modules.blog.service.article.ArticleService;
import top.ooovo.framework.common.pojo.PageResult;

import javax.annotation.Resource;

/**
 * 文章 Service 实现类
 * @author QAQ
 * @date 2022/5/7
 */

@Service
@Slf4j
@Validated
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public PageResult<ArticleDO> listArchives() {
        // TODO 归档
        return null;
    }

    @Override
    public ArticleDO getArticle(Long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public ArticleDO getArticleByCode(String code) {
        return articleMapper.selectByCode(code);
    }

    @Override
    public PageResult<ArticleDO> getArticlePage(ArticlePageReqVO reqVO) {
        return articleMapper.selectPage(reqVO);
    }

    @Override
    public void updateArticleTop(Long id) {
        // TODO 添加置顶功能
    }

    @Override
    public void deleteArticle(Long id) {
        articleMapper.deleteById(id);
    }

    @Override
    public String createArticle(ArticleCreateReqVO reqVO) {

        // TODO 创建文章
        return null;
    }

    @Override
    public void updateArticle(ArticleUpdateReqVO reqVO) {
        // TODO save article
    }
}
