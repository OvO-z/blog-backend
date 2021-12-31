package top.ooovo.blog.service.modules.infra.service.logger.impl;

import lombok.extern.slf4j.Slf4j;
import top.ooovo.blog.service.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogPageReqVO;
import top.ooovo.blog.service.modules.infra.convert.logger.InfApiAccessLogConvert;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import top.ooovo.blog.service.modules.infra.dal.mysql.logger.InfApiAccessLogMapper;
import top.ooovo.blog.service.modules.infra.service.logger.InfApiAccessLogService;
import top.ooovo.framework.apilog.core.service.dto.ApiAccessLogCreateReqDTO;
import top.ooovo.framework.common.pojo.PageResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * API 访问日志 Service 实现类
 * @author QAQ
 * @date 2021/11/25
 */

@Service
@Validated
@Slf4j
public class InfApiAccessLogServiceImpl implements InfApiAccessLogService {

    @Resource
    InfApiAccessLogMapper apiAccessLogMapper;

    @Override
    @Async
    public void createApiAccessLogAsync(@Valid ApiAccessLogCreateReqDTO createDTO) {
        InfApiAccessLogDO apiAccessLog = InfApiAccessLogConvert.INSTANCE.convert(createDTO);
        apiAccessLogMapper.insert(apiAccessLog);
    }

    @Override
    public PageResult<InfApiAccessLogDO> getApiAccessLogPage(InfApiAccessLogPageReqVO pageReqVO) {
        return apiAccessLogMapper.selectPage(pageReqVO);
    }
}
