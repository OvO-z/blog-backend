package top.ooovo.blog.service.modules.blog.controller.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author QAQ
 * @date 2022/5/7
 */

@ApiModel("文章创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticleUpdateReqVO extends ArticleBaseVO {

    /**
     * 文章id
     */
    @NotBlank(message = "文章ID不能为空")
    @ApiModelProperty(name = "id", value = "文章id")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(name = "title", value = "文章标题", required = true)
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(name = "content", value = "文章内容", required = true)
    private String content;

    /**
     * 文章分类
     */
    @ApiModelProperty(name = "categoryId", value = "文章分类")
    private Integer categoryId;
}
