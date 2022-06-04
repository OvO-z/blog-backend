package top.ooovo.blog.service.modules.blog.controller.article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ooovo.blog.service.modules.blog.controller.article.vo.ArticleRespVO;
import top.ooovo.blog.service.modules.blog.service.article.ArticleService;
import top.ooovo.framework.common.pojo.CommonResult;

import javax.annotation.Resource;

import static org.springframework.boot.actuate.autoconfigure.cloudfoundry.SecurityResponse.success;

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
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Integer")
    @GetMapping("/admin/articles/{articleId}")
    public CommonResult<ArticleRespVO> getArticleBackById(@PathVariable("articleId") Integer articleId) {
        // TODO articlecontroller
        return null;
    }
}
