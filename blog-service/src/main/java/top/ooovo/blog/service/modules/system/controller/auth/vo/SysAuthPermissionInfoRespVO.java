package top.ooovo.blog.service.modules.system.controller.auth.vo;

/**
 * @author: GJH
 * @date: 2021/12/6 20:16
 * @description:
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@ApiModel(value = "登录用户的权限信息 Response VO", description = "额外包括用户信息和角色列表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysAuthPermissionInfoRespVO {

    @ApiModelProperty(value = "用户信息", required = true)
    private UserVO user;

    @ApiModelProperty(value = "角色标识数组", required = true)
    private Set<String> roles;

    @ApiModelProperty(value = "操作权限数组", required = true)
    private Set<String> permissions;

    @ApiModel("用户信息 VO")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserVO {

        @ApiModelProperty(value = "用户昵称", required = true, example = "QAQ写的")
        private String nickname;

        @ApiModelProperty(value = "用户头像", required = true, example = "头像图片链接")
        private String avatar;

    }

}
