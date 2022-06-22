package top.ooovo.blog.service.modules.blog.controller.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static top.ooovo.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * @author QAQ
 * @date 2022/6/13
 */


@ApiModel("文章信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSimpleRespVO {

    /**
     * 文章id
     */
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
    @ApiModelProperty(name = "categoryName", value = "文章分类")
    private String categoryName;

    /**
     * 文章状态 1.公开 2.私密 3.评论可见
     */
    @ApiModelProperty(name = "status", value = "文章状态")
    private Integer status;

    @ApiModelProperty(value = "发布时间", example = "2020-10-24 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date createTime;
}
