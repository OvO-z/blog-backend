package top.ooovo.blog.service.modules.blog.service;

import top.ooovo.blog.service.modules.blog.dal.dataobject.BlogArticleDO;
import top.ooovo.framework.common.pojo.PageResult;

/**
 * 博客文章 Service 接口
 * @author QAQ
 * @date 2022/5/6
 */
public interface BlogArticleService {

    /**
     * 查询文章归档
     * @return 文章归档
     */
    PageResult<BlogArticleDO> listArchives();


    /**
     * 修改文章置顶
     *
     */
    void updateArticleTop();
}
