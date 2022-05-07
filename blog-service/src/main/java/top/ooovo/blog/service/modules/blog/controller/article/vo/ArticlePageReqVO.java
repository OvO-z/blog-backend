package top.ooovo.blog.service.modules.blog.controller.article.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.ooovo.framework.common.pojo.PageParam;

import java.util.Date;

import static top.ooovo.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * @author QAQ
 * @date 2022/5/7
 */

@ApiModel("博客文章分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticlePageReqVO extends PageParam {

    @ApiModelProperty(value = "文章编号", example = "666")
    private String code;

    @ApiModelProperty(value = "分类ID", example = "2")
    private Long categoryId;

    @ApiModelProperty(value = "文章标题", example = "博客")
    private String title;

    @ApiModelProperty(value = "文章类型", example = "2")
    private Integer type;

    @ApiModelProperty(value = "文章状态", example = "1")
    private Integer status;

    @ApiModelProperty(value = "开始时间", example = "2020-10-24 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date beginTime;

    @ApiModelProperty(value = "结束时间", example = "2020-10-24 23:59:59")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endTime;
}
