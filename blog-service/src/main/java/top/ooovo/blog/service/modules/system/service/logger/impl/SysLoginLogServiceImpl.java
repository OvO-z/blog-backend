package top.ooovo.blog.service.modules.system.service.logger.impl;

import top.ooovo.blog.service.modules.system.controller.logger.vo.SysLoginLogPageReqVO;
import top.ooovo.blog.service.modules.system.convert.logger.SysLoginLogConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.logger.SysLoginLogDO;
import top.ooovo.blog.service.modules.system.dal.mysql.logger.SysLoginLogMapper;
import top.ooovo.blog.service.modules.system.service.logger.SysLoginLogService;
import top.ooovo.blog.service.modules.system.service.logger.dto.SysLoginLogCreateReqDTO;
import top.ooovo.framework.common.pojo.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录日志Service 实现
 * @author QAQ
 * @date 2021/12/1
 */

@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Resource
    private SysLoginLogMapper loginLogMapper;

    @Override
    public void createLoginLog(SysLoginLogCreateReqDTO reqDTO) {
        SysLoginLogDO loginLogDO = SysLoginLogConvert.INSTANCE.convert(reqDTO);

        loginLogMapper.insert(loginLogDO);
    }

    @Override
    public PageResult<SysLoginLogDO> getLoginLogPage(SysLoginLogPageReqVO reqVO) {
        return loginLogMapper.selectPage(reqVO);
    }
}
