package top.ooovo.blog.service.modules.blog.controller.article;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QAQ
 * @date 2022/5/6
 */
@Api(tags = "博客文章")
@RestController
@RequestMapping("/article")
@Validated
@Slf4j
public class ArticleController {
}
