package top.ooovo.blog.service.modules.blog.controller.category.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author QAQ
 * @date 2022/6/4
 */

@ApiModel("分类创建 Request VO")
@Data
@ToString(callSuper = true)
public class CategoryCreateReqVO {

    @NotBlank(message = "分类名不能为空")
    @ApiModelProperty(name = "name", value = "分类名", required = true, dataType = "String")
    private String name;

}
