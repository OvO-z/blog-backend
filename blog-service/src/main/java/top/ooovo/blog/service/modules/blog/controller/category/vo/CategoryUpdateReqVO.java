package top.ooovo.blog.service.modules.blog.controller.category.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author QAQ
 * @date 2022/6/4
 */

@ApiModel("分类更新 Request VO")
@Data
@ToString(callSuper = true)
public class CategoryUpdateReqVO {

    @ApiModelProperty(value = "分类编号", required = true, example = "1024")
    @NotNull(message = "分类编号不能为空")
    private Long id;

    @ApiModelProperty(name = "name", value = "分类名", required = true, dataType = "String")
    private String name;

    @ApiModelProperty(name = "count", value = "文章总数", required = true, dataType = "Integer")
    private Integer count;
}
