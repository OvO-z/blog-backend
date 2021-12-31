package top.ooovo.blog.service.modules.system.controller.logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ooovo.blog.service.modules.system.controller.logger.vo.SysLoginLogPageReqVO;
import top.ooovo.blog.service.modules.system.controller.logger.vo.SysLoginLogRespVO;
import top.ooovo.blog.service.modules.system.convert.logger.SysLoginLogConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.logger.SysLoginLogDO;
import top.ooovo.blog.service.modules.system.service.logger.SysLoginLogService;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.pojo.PageResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static top.ooovo.framework.common.pojo.CommonResult.success;

/**
 * @author QAQ
 * @date 2021/12/6
 */

@Api(tags = "登录日志")
@RestController
@RequestMapping("/system/login-log")
@Validated
public class SysLoginLogController {

    @Resource
    private SysLoginLogService loginLogService;

    @GetMapping("/page")
    @ApiOperation("获得登录日志分页列表")
    @PreAuthorize("@ss.hasPermission('system:login-log:query')")
    public CommonResult<PageResult<SysLoginLogRespVO>> getLoginLogPage(@Valid SysLoginLogPageReqVO reqVO) {
        PageResult<SysLoginLogDO> page = loginLogService.getLoginLogPage(reqVO);
        return success(SysLoginLogConvert.INSTANCE.convertPage(page));
    }
}
