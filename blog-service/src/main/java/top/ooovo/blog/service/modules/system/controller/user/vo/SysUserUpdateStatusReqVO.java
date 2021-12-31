package top.ooovo.blog.service.modules.system.controller.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author QAQ
 * @date 2021/12/4
 */

@ApiModel("用户更新状态 Request VO")
@Data
public class SysUserUpdateStatusReqVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    @NotNull(message = "角色编号不能为空")
    private Long id;

    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "见 SysCommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    private Integer status;

}