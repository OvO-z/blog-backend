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

@Api(tags = "认证")
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
    @ApiOperation("使用账号密码登录")
    public CommonResult<SysAuthLoginRespVO> login(@RequestBody @Valid SysAuthLoginReqVO reqVO) {
        String token = authService.login(reqVO, getClientIP(), getUserAgent());
        // 返回结果
        return success(SysAuthLoginRespVO.builder().token(token).build());
    }

    @GetMapping("/get-permission-info")
    @ApiOperation("获取登录用户的权限信息")
    public CommonResult<SysAuthPermissionInfoRespVO> getPermissionInfo(){
        //获得用户信息
        SysUserDO user = userService.getUser(getLoginUserId());
        if (user == null) {
            return null;
        }
        // 获得角色列表
        List<SysRoleDO> roleList = roleService.getRolesFromCache(getLoginUserRoleIds());
        // 获得菜单列表
        List<SysMenuDO> menuList = permissionService.getRoleMenusFromCache(
                // 注意，基于登录的角色，因为后续的权限判断也是基于它
                getLoginUserRoleIds(),
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType(), MenuTypeEnum.BUTTON.getType()),
                SetUtils.asSet(CommonStatusEnum.ENABLE.getStatus()));
        // 拼接结果返回
        return success(SysAuthConvert.INSTANCE.convert(user, roleList, menuList));
    }

    @GetMapping("list-menus")
    @ApiOperation("获得登录用户的菜单列表")
    public CommonResult<List<SysAuthMenuRespVO>> getMenus(){
        // 获得用户拥有的菜单列表
        List<SysMenuDO> menuList = permissionService.getRoleMenusFromCache(
                // 注意，基于登录的角色，因为后续的权限判断也是基于它
                getLoginUserRoleIds(),
                // 只要目录和菜单类型
                SetUtils.asSet(MenuTypeEnum.DIR.getType(),MenuTypeEnum.MENU.getType()),
                // 只要开启的
                SetUtils.asSet(CommonStatusEnum.ENABLE.getStatus()));
        // 转换成 Tree 结构返回
        return success(SysAuthConvert.INSTANCE.buildMenuTree(menuList));
    }

}
