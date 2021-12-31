package top.ooovo.blog.service.modules.infra.service.logger;

import top.ooovo.blog.service.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import top.ooovo.framework.apilog.core.service.ApiErrorLogFrameworkService;
import top.ooovo.framework.common.pojo.PageResult;

/**
 * API 错误日志 Service 接口
 * @author QAQ
 * @date 2021/11/25
 */

public interface InfApiErrorLogService extends ApiErrorLogFrameworkService {

    /**
     * 获得 API 错误日志分页
     *
     * @param pageReqVO 分页查询
     * @return API 错误日志分页
     */
    PageResult<InfApiErrorLogDO> getApiErrorLogPage(InfApiErrorLogPageReqVO pageReqVO);


    /**
     * 更新 API 错误日志已处理
     *
     * @param id API 日志编号
     * @param processStatus 处理结果
     * @param processUserId 处理人
     */
    void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId);

}
