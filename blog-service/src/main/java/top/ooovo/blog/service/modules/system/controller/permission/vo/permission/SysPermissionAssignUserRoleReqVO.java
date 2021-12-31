package top.ooovo.blog.service.modules.system.controller.permission.vo.permission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

/**
 * @author QAQ
 * @date 2021/12/1
 */

@ApiModel("赋予用户角色 Request VO")
@Data
public class SysPermissionAssignUserRoleReqVO {
    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    @NotNull(message = "角色编号不能为空")
    private Long userId;

    @ApiModelProperty(value = "角色编号列表", example = "1,3,5")
    // 兜底
    private Set<Long> roleIds = Collections.emptySet();
}
