package top.ooovo.blog.service.modules.system.controller.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 用户 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 * @author QAQ
 * @date 2021/11/23
 */

@Data
public class SysUserBaseVO {

    @ApiModelProperty(value = "用户账号", required = true, example = "admin")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String username;

    @ApiModelProperty(value = "用户昵称", required = true, example = "默认用户")
    @Size(max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickname;

    @ApiModelProperty(value = "备注", example = "我是一个用户")
    private String remark;
}
