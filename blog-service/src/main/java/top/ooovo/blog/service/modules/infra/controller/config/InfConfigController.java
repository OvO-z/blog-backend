package top.ooovo.blog.service.modules.infra.controller.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigCreateReqVO;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigPageReqVO;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigRespVO;
import top.ooovo.blog.service.modules.infra.controller.config.vo.InfConfigUpdateReqVO;
import top.ooovo.blog.service.modules.infra.convert.config.InfConfigConvert;
import top.ooovo.blog.service.modules.infra.dal.dataobject.config.InfConfigDO;
import top.ooovo.blog.service.modules.infra.service.config.InfConfigService;
import top.ooovo.framework.common.pojo.CommonResult;
import top.ooovo.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import javax.validation.Valid;

import static top.ooovo.blog.service.modules.infra.enums.InfErrorCodeConstants.CONFIG_GET_VALUE_ERROR_IF_SENSITIVE;
import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;
import static top.ooovo.framework.common.pojo.CommonResult.success;

/**
 * @author QAQ
 * @date 2022/5/6
 */

@Api(tags = "参数配置")
@RestController
@RequestMapping("/infra/config")
@Validated
public class InfConfigController {
    @Resource
    private InfConfigService configService;

    @PostMapping("/create")
    @ApiOperation("创建参数配置")
    @PreAuthorize("@ss.hasPermission('infra:config:create')")
    public CommonResult<Long> createConfig(@Valid @RequestBody InfConfigCreateReqVO reqVO) {
        return success(configService.createConfig(reqVO));
    }

    @PutMapping("/update")
    @ApiOperation("修改参数配置")
    @PreAuthorize("@ss.hasPermission('infra:config:update')")
    public CommonResult<Boolean> updateConfig(@Valid @RequestBody InfConfigUpdateReqVO reqVO) {
        configService.updateConfig(reqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除参数配置")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('infra:config:delete')")
    public CommonResult<Boolean> deleteConfig(@RequestParam("id") Long id) {
        configService.deleteConfig(id);
        return success(true);
    }

    @GetMapping(value = "/get")
    @ApiOperation("获得参数配置")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('infra:config:query')")
    public CommonResult<InfConfigRespVO> getConfig(@RequestParam("id") Long id) {
        return success(InfConfigConvert.INSTANCE.convert(configService.getConfig(id)));
    }

    @GetMapping(value = "/get-value-by-key")
    @ApiOperation(value = "根据参数键名查询参数值", notes = "敏感配置，不允许返回给前端")
    @ApiImplicitParam(name = "key", value = "参数键", required = true, example = "biz.username", dataTypeClass = String.class)
    public CommonResult<String> getConfigKey(@RequestParam("key") String key) {
        InfConfigDO config = configService.getConfigByKey(key);
        if (config == null) {
            return null;
        }
        if (config.getSensitive()) {
            throw exception(CONFIG_GET_VALUE_ERROR_IF_SENSITIVE);
        }
        return success(config.getValue());
    }

    @GetMapping("/page")
    @ApiOperation("获取参数配置分页")
    @PreAuthorize("@ss.hasPermission('infra:config:query')")
    public CommonResult<PageResult<InfConfigRespVO>> getConfigPage(@Valid InfConfigPageReqVO reqVO) {
        PageResult<InfConfigDO> page = configService.getConfigPage(reqVO);
        return success(InfConfigConvert.INSTANCE.convertPage(page));
    }
}
