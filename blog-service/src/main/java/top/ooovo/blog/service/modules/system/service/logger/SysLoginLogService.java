package top.ooovo.blog.service.modules.system.service.logger;

import top.ooovo.blog.service.modules.system.controller.logger.vo.SysLoginLogPageReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.logger.SysLoginLogDO;
import top.ooovo.blog.service.modules.system.service.logger.dto.SysLoginLogCreateReqDTO;
import top.ooovo.framework.common.pojo.PageResult;

/**
 * 登录日志 Service 接口
 * @author QAQ
 * @date 2021/12/1
 */

public interface SysLoginLogService {

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    void createLoginLog(SysLoginLogCreateReqDTO reqDTO);


    /**
     * 获得登录日志分页
     *
     * @param reqVO 分页条件
     * @return 登录日志分页
     */
    PageResult<SysLoginLogDO> getLoginLogPage(SysLoginLogPageReqVO reqVO);
}
