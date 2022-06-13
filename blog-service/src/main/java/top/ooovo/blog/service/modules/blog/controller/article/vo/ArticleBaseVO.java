package top.ooovo.blog.service.modules.blog.controller.article.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QAQ
 * @date 2022/5/7
 */

@Data
public class ArticleBaseVO {

    /**
     * 文章类型
     */
    @ApiModelProperty(name = "type", value = "文章类型", dataType = "Integer")
    private Integer type;

    /**
     * 文章状态 1.公开 2.私密 3.评论可见
     */
    @ApiModelProperty(name = "status", value = "文章状态", dataType = "Integer")
    private Integer status;

    /**
     * 是否置顶
     */
    @ApiModelProperty(name = "isTop", value = "是否置顶", dataType = "Integer")
    private Integer isTop;

    /**
     * 文章封面
     */
    @ApiModelProperty(name = "articleCover", value = "文章缩略图", dataType = "String")
    private String cover;
}
