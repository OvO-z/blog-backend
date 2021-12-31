package top.ooovo.blog.service.modules.system.controller.permission.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("角色精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleSimpleRespVO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "角色名称", required = true, example = "管理员")
    private String name;

}
