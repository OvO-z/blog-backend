package top.ooovo.blog.service.modules.system.controller.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author QAQ
 * @date 2021/11/23
 */

@ApiModel("用户创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserCreateReqVO extends SysUserBaseVO {

    @ApiModelProperty(value = "密码", required = true, example = "123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;
}
