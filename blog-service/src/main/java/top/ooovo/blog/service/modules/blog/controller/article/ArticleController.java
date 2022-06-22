package top.ooovo.blog.service.modules.blog.controller.article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleCreateReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticlePageReqVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleRespVO;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleUpdateReqVO;
import top.ooovo.blog.service.modules.blog.convert.article.ArticleConvert;
import top.ooovo.blog.service.modules.blog.service.article.ArticleService;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import javax.validation.Valid;

import static top.ooovo.framework.common.pojo.CommonResult.success;


/**
 * @author QAQ
 * @date 2022/5/6
 */
@Api(tags = "博客文章")
@RestController
@RequestMapping("/blog/article")
@Validated
@Slf4j
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation("根据id查看文章")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Long")
    @GetMapping("/{articleId}")
    public CommonResult<ArticleRespVO> getArticleBackById(@PathVariable("articleId") Long articleId) {
        return success(ArticleConvert.INSTANCE.convert(articleService.getArticle(articleId)));
    }

    @PostMapping("/create")
    @ApiOperation("创建文章")
    @PreAuthorize("@ss.hasPermission('blog:article:create')")
    public CommonResult<Long> createArticle(@Valid @RequestBody ArticleCreateReqVO reqVO) {
        Long id = articleService.createArticle(reqVO);
        return success(id);
    }

    @PutMapping("/update")
    @ApiOperation("修改文章")
    @PreAuthorize("@ss.hasPermission('blog:article:update')")
    public CommonResult<Boolean> updateArticle(@Valid @RequestBody ArticleUpdateReqVO reqVO) {
        articleService.updateArticle(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete/{articleId}")
    @ApiOperation("删除文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('blog:article:delete')")
    public CommonResult<Boolean> deleteArticle(@RequestBody Long id) {
        articleService.deleteArticle(id);
        return success(true);
    }

    @GetMapping("/page")
    @ApiOperation("获得文章分页列表")
    @PreAuthorize("@ss.hasPermission('blog:category:page')")
    public CommonResult<PageResult<ArticleRespVO>> pageArticles(@Valid ArticlePageReqVO reqVO) {
        return success(ArticleConvert.INSTANCE.convertPage(articleService.getArticlePage(reqVO)));
    }

}
