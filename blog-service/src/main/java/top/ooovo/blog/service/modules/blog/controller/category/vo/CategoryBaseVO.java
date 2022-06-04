package top.ooovo.blog.service.modules.blog.controller.category.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author QAQ
 * @date 2022/6/4
 */

@Data
public class CategoryBaseVO {

    @ApiModelProperty(name = "id", value = "分类id", dataType = "Integer")
    private Integer id;

    @NotBlank(message = "分类名不能为空")
    @ApiModelProperty(name = "categoryName", value = "分类名", required = true, dataType = "String")
    private String categoryName;

}
