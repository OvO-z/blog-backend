package top.ooovo.blog.service.modules.infra.controller.logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.ooovo.blog.service.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogPageReqVO;
import top.ooovo.blog.service.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogRespVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import top.ooovo.blog.service.modules.infra.convert.logger.InfApiAccessLogConvert;
import top.ooovo.blog.service.modules.infra.service.logger.InfApiAccessLogService;
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

@Api(tags = "API 访问日志")
@RestController
@RequestMapping("/infra/api-access-log")
@Validated
public class InfApiAccessLogController {

    @Resource
    private InfApiAccessLogService apiAccessLogService;

    @GetMapping("/page")
    @ApiOperation("获得API 访问日志分页")
    @PreAuthorize("@ss.hasPermission('infra:api-access-log:query')")
    public CommonResult<PageResult<InfApiAccessLogRespVO>> getApiAccessLogPage(@Valid InfApiAccessLogPageReqVO pageVO) {
        PageResult<InfApiAccessLogDO> pageResult = apiAccessLogService.getApiAccessLogPage(pageVO);
        return success(InfApiAccessLogConvert.INSTANCE.convertPage(pageResult));
    }

}
