package top.ooovo.blog.service.modules.infra.service.logger;

import top.ooovo.blog.service.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogPageReqVO;
import top.ooovo.blog.service.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import top.ooovo.framework.apilog.core.service.ApiAccessLogFrameworkService;
import top.ooovo.framework.common.pojo.PageResult;

/**
 * API 访问日志 Service 接口
 * @author QAQ
 * @date 2021/11/25
 */

public interface InfApiAccessLogService extends ApiAccessLogFrameworkService {


    /**
     * 获得 API 访问日志分页
     *
     * @param pageReqVO 分页查询
     * @return API 访问日志分页
     */
    PageResult<InfApiAccessLogDO> getApiAccessLogPage(InfApiAccessLogPageReqVO pageReqVO);
}
