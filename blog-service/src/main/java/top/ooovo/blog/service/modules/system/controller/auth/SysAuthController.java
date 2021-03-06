package top.ooovo.blog.service.modules.system.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import top.ooovo.blog.service.modules.system.controller.auth.vo.SysAuthLoginReqVO;
import top.ooovo.blog.service.modules.system.controller.auth.vo.SysAuthLoginRespVO;
import top.ooovo.blog.service.modules.system.controller.auth.vo.SysAuthMenuRespVO;
import top.ooovo.blog.service.modules.system.controller.auth.vo.SysAuthPermissionInfoRespVO;
import top.ooovo.blog.service.modules.system.convert.auth.SysAuthConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysMenuDO;
import top.ooovo.blog.service.modules.system.dal.dataobject.permission.SysRoleDO;
import top.ooovo.blog.service.modules.system.dal.dataobject.user.SysUserDO;
import top.ooovo.blog.service.modules.system.enums.permission.MenuTypeEnum;
import top.ooovo.blog.service.modules.system.service.auth.SysAuthService;
import top.ooovo.blog.service.modules.system.service.permission.SysPermissionService;
import top.ooovo.blog.service.modules.system.service.permission.SysRoleService;
import top.ooovo.blog.service.modules.system.service.user.SysUserService;
import top.ooovo.framework.common.enums.CommonStatusEnum;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.util.collection.SetUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static top.ooovo.framework.common.pojo.CommonResult.success;
import static top.ooovo.framework.common.util.servlet.ServletUtils.getClientIP;
import static top.ooovo.framework.common.util.servlet.ServletUtils.getUserAgent;
import static top.ooovo.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static top.ooovo.framework.security.core.util.SecurityFrameworkUtils.getLoginUserRoleIds;

/**
 * @author QAQ
 * @date 2021/12/2
 */

@Api(tags = "??????")
@RestController
@RequestMapping("/")
@Validated
@Slf4j
public class SysAuthController {

    @Resource
    private SysAuthService authService;

    @Resource
    private SysUserService userService;

    @Resource
    private SysRoleService roleService;

    @Resource
    private SysPermissionService permissionService;

    @PostMapping("/login")
    @ApiOperation("????????????????????????")
    public CommonResult<SysAuthLoginRespVO> login(@RequestBody @Valid SysAuthLoginReqVO reqVO) {
        String token = authService.login(reqVO, getClientIP(), getUserAgent());
        // ????????????
        return success(SysAuthLoginRespVO.builder().token(token).build());
    }

    @GetMapping("/get-permission-info")
    @ApiOperation("?????????????????????????????????")
    public CommonResult<SysAuthPermissionInfoRespVO> getPermissionInfo(){
        //??????????????????
        SysUserDO user = userService.getUser(getLoginUserId());
        if (user == null) {
            return null;
        }
        // ??????????????????
        List<SysRoleDO> roleList = roleService.getRolesFromCache(getLoginUserRoleIds());
        // ??????????????????
        List<SysMenuDO> menuList = permissionService.getRoleMenusFromCache(
                // ???????????????????????????????????????????????????????????????????????????
                getLoginUserRoleIds(),
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType(), MenuTypeEnum.BUTTON.getType()),
                SetUtils.asSet(CommonStatusEnum.ENABLE.getStatus()));
        // ??????????????????
        return success(SysAuthConvert.INSTANCE.convert(user, roleList, menuList));
    }

    @GetMapping("list-menus")
    @ApiOperation("?????????????????????????????????")
    public CommonResult<List<SysAuthMenuRespVO>> getMenus(){
        // ?????????????????????????????????
        List<SysMenuDO> menuList = permissionService.getRoleMenusFromCache(
                // ???????????????????????????????????????????????????????????????????????????
                getLoginUserRoleIds(),
                // ???????????????????????????
                SetUtils.asSet(MenuTypeEnum.DIR.getType(),MenuTypeEnum.MENU.getType()),
                // ???????????????
                SetUtils.asSet(CommonStatusEnum.ENABLE.getStatus()));
        // ????????? Tree ????????????
        return success(SysAuthConvert.INSTANCE.buildMenuTree(menuList));
    }

}
