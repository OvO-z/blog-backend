package top.ooovo.blog.service.modules.blog.controller.category.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

import static top.ooovo.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@ApiModel("分类信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRespVO {

    @ApiModelProperty(value = "创建时间", required = true, example = "2020-10-24 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date createTime;

    @ApiModelProperty(name = "name", value = "分类名", required = true, dataType = "String")
    private String name;

    @ApiModelProperty(name = "count", value = "文章总数", required = true, dataType = "Integer")
    private Integer count;
}
