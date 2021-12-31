package top.ooovo.blog.service.modules.system.controller.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import top.ooovo.blog.service.modules.system.controller.permission.vo.role.*;
import top.ooovo.blog.service.modules.system.convert.permission.SysRoleConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysRoleDO;
import top.ooovo.blog.service.modules.system.service.permission.SysRoleService;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.pojo.PageResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static top.ooovo.framework.common.pojo.CommonResult.success;

/**
 * @author QAQ
 * @date 2021/11/29
 */

@Api(tags = "角色")
@RestController
@RequestMapping("/system/role")
@Validated
public class SysRoleController {

    @Resource
    private SysRoleService roleService;

    @PostMapping("/create")
    @ApiOperation("创建角色")
    @PreAuthorize("@ss.hasPermission('system:role:create')")
    public CommonResult<Long> createRole(@Valid @RequestBody SysRoleCreateReqVO reqVO) {
        return success(roleService.createRole(reqVO));
    }

    @PutMapping("/update")
    @ApiOperation("修改角色")
    @PreAuthorize("@ss.hasPermission('system:role:update')")
    public CommonResult<Boolean> updateRole(@Valid @RequestBody SysRoleUpdateReqVO reqVO) {
        roleService.updateRole(reqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @ApiOperation("修改角色状态")
    @PreAuthorize("@ss.hasPermission('system:role:update')")
    public CommonResult<Boolean> updateRoleStatus(@Valid @RequestBody SysRoleUpdateStatusReqVO reqVO) {
        roleService.updateRoleStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:role:delete')")
    public CommonResult<Boolean> deleteRole(@RequestParam("id") Long id) {
        roleService.deleteRole(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得角色信息")
    @PreAuthorize("@ss.hasPermission('system:role:query')")
    public CommonResult<SysRoleRespVO> getRole(@RequestParam("id") Long id) {
        SysRoleDO role = roleService.getRole(id);
        return success(SysRoleConvert.INSTANCE.convert(role));
    }

    @GetMapping("/page")
    @ApiOperation("获得角色分页")
    @PreAuthorize("@ss.hasPermission('system:role:query')")
    public CommonResult<PageResult<SysRoleDO>> getRolePage(SysRolePageReqVO reqVO) {
        return success(roleService.getRolePage(reqVO));
    }

}
