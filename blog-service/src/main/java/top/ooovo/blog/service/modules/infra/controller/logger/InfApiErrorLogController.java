package top.ooovo.blog.service.modules.infra.controller.logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.ooovo.blog.service.modules.infra.convert.logger.InfApiErrorLogConvert;
import top.ooovo.blog.service.modules.infra.service.logger.InfApiErrorLogService;
import top.ooovo.blog.service.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import top.ooovo.blog.service.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogRespVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.pojo.PageResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static top.ooovo.framework.common.pojo.CommonResult.success;
import static top.ooovo.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * @author QAQ
 * @date 2021/12/6
 */

@Api(tags = "API 错误日志")
@RestController
@RequestMapping("/infra/api-error-log")
@Validated
public class InfApiErrorLogController {

    @Resource
    private InfApiErrorLogService apiErrorLogService;

    @PutMapping("/update-status")
    @ApiOperation("更新 API 错误日志的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "processStatus", value = "处理状态", required = true, example = "1", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:update-status')")
    public CommonResult<Boolean> updateApiErrorLogProcess(@RequestParam("id") Long id,
                                                          @RequestParam("processStatus") Integer processStatus) {
        apiErrorLogService.updateApiErrorLogProcess(id, processStatus, getLoginUserId());
        return success(true);
    }

    @GetMapping("/page")
    @ApiOperation("获得 API 错误日志分页")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:query')")
    public CommonResult<PageResult<InfApiErrorLogRespVO>> getApiErrorLogPage(@Valid InfApiErrorLogPageReqVO pageVO) {
        PageResult<InfApiErrorLogDO> pageResult = apiErrorLogService.getApiErrorLogPage(pageVO);
        return success(InfApiErrorLogConvert.INSTANCE.convertPage(pageResult));
    }
}
