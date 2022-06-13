package top.ooovo.blog.service.modules.blog.service.article.impl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticlePageReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleUpdateReqVO;
import top.ooovo.blog.service.modules.blog.convert.article.ArticleConvert;
import top.ooovo.blog.service.modules.blog.dal.dataobject.article.ArticleDO;
import top.ooovo.blog.service.modules.blog.dal.dataobject.category.CategoryDO;
import top.ooovo.blog.service.modules.blog.dal.mysql.article.ArticleMapper;
import top.ooovo.blog.service.modules.blog.service.article.ArticleService;
import top.ooovo.framework.common.pojo.PageResult;

import javax.annotation.Resource;

import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.*;
import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.CATEGORY_NAME_EXISTS;
import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;

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
    public void deleteArticle(Long id) {
        articleMapper.deleteById(id);
    }

    @Override
    public Long createArticle(ArticleCreateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(null, reqVO.getTitle());
        ArticleDO article = ArticleConvert.INSTANCE.convert(reqVO);
        articleMapper.insert(article);

        return article.getId();
    }

    @Override
    public void updateArticle(ArticleUpdateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(null, reqVO.getTitle());
        ArticleDO article = ArticleConvert.INSTANCE.convert(reqVO);
        articleMapper.updateById(article);
    }

    /**
     * 检查修改或创建
     * @param id
     * @param name
     */
    private void checkCreateOrUpdate(Long id, String name) {
        // 校验文章存在
        this.checkArticleExists(id);
        // 校验文章名称唯一
        this.checkNameUnique(id, name);
    }


    /**
     * 检查文章是否存在
     * @param id
     */
    private void checkArticleExists(Long id) {
        if (id == null) {
            return;
        }
        ArticleDO article = articleMapper.selectById(id);
        if (article == null) {
            throw exception(ARTICLE_NOT_EXISTS);
        }
    }

    /**
     * 检查分类名唯一性
     * @param id
     * @param title
     */
    private void checkNameUnique(Long id, String title) {
        if (StrUtil.isBlank(title)) {
            return;
        }
        ArticleDO article = articleMapper.selectByTitle(title);
        if (article == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的分类
        if (id == null) {
            throw exception(ARTICLE_NAME_EXISTS);
        }
        if (!article.getId().equals(id)) {
            throw exception(ARTICLE_NAME_EXISTS);
        }
    }
}
