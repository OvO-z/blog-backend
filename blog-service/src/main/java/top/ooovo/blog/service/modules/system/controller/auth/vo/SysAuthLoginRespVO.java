package top.ooovo.blog.service.modules.system.controller.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QAQ
 */
@ApiModel("账号密码登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAuthLoginRespVO {

    @ApiModelProperty(value = "token", required = true, example = "token string")
    private String token;

}
