package top.ooovo.blog.service.modules.system.controller.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ooovo.blog.service.modules.system.controller.permission.vo.permission.SysPermissionAssignUserRoleReqVO;
import top.ooovo.blog.service.modules.system.service.permission.SysPermissionService;
import top.ooovo.framework.common.pojo.CommonResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static top.ooovo.framework.common.pojo.CommonResult.success;

/**
 * 权限 Controller，提供赋予用户、角色的权限的 API 接口
 *
 * @author QAQ
 * @date 2021/12/1
 */
@Api(tags = "权限")
@RestController
@RequestMapping("/system/permission")
public class SysPermissionController {

    @Resource
    private SysPermissionService permissionService;

    @ApiOperation("赋予用户角色")
    @PostMapping("/assign-user-role")
    public CommonResult<Boolean> assignUserRole(@Validated @RequestBody SysPermissionAssignUserRoleReqVO reqVO) {
        permissionService.assignUserRole(reqVO.getUserId(), reqVO.getRoleIds());
        return success(true);
    }
}
