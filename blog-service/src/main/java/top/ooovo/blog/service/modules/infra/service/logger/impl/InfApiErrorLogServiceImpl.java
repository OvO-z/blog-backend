package top.ooovo.blog.service.modules.infra.service.logger.impl;

import lombok.extern.slf4j.Slf4j;
import top.ooovo.blog.service.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import top.ooovo.blog.service.modules.infra.convert.logger.InfApiErrorLogConvert;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import top.ooovo.blog.service.modules.infra.dal.mysql.logger.InfApiErrorLogMapper;
import top.ooovo.blog.service.modules.infra.enums.logger.InfApiErrorLogProcessStatusEnum;
import top.ooovo.blog.service.modules.infra.service.logger.InfApiErrorLogService;
import top.ooovo.framework.apilog.core.service.dto.ApiErrorLogCreateReqDTO;
import top.ooovo.framework.common.pojo.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

import static top.ooovo.blog.service.modules.infra.enums.InfErrorCodeConstants.API_ERROR_LOG_NOT_FOUND;
import static top.ooovo.blog.service.modules.infra.enums.InfErrorCodeConstants.API_ERROR_LOG_PROCESSED;
import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @author QAQ
 * @date 2021/11/25
 */

@Slf4j
@Service
@Validated
public class InfApiErrorLogServiceImpl implements InfApiErrorLogService {

    @Resource
    private InfApiErrorLogMapper apiErrorLogMapper;

    @Override
    public void createApiErrorLogAsync(@Valid ApiErrorLogCreateReqDTO createDTO) {
        InfApiErrorLogDO apiErrorLog = InfApiErrorLogConvert.INSTANCE.convert(createDTO);
        apiErrorLog.setProcessStatus(InfApiErrorLogProcessStatusEnum.INIT.getStatus());
        apiErrorLogMapper.insert(apiErrorLog);
    }

    @Override
    public PageResult<InfApiErrorLogDO> getApiErrorLogPage(InfApiErrorLogPageReqVO pageReqVO) {
        return apiErrorLogMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId) {
        InfApiErrorLogDO errorLog = apiErrorLogMapper.selectById(id);

        if (errorLog == null) {
            throw exception(API_ERROR_LOG_NOT_FOUND);
        }

        if (!InfApiErrorLogProcessStatusEnum.INIT.getStatus().equals(errorLog.getProcessStatus())) {
            throw exception(API_ERROR_LOG_PROCESSED);
        }

        // 标记处理
        apiErrorLogMapper.updateById(InfApiErrorLogDO.builder().id(id).processStatus(processStatus)
                .processUserId(processUserId).processTime(new Date()).build());
    }
}
