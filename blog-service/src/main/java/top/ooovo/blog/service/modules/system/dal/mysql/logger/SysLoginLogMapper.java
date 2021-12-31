package top.ooovo.blog.service.modules.system.dal.mysql.logger;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.system.controller.logger.vo.SysLoginLogPageReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.logger.SysLoginLogDO;
import top.ooovo.blog.service.modules.system.enums.logger.SysLoginResultEnum;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

/**
 * @author QAQ
 * @date 2021/12/1
 */

@Mapper
public interface SysLoginLogMapper extends BaseMapperX<SysLoginLogDO> {

    default PageResult<SysLoginLogDO> selectPage(SysLoginLogPageReqVO reqVO) {
        QueryWrapperX<SysLoginLogDO> query = new QueryWrapperX<SysLoginLogDO>()
                .likeIfPresent("user_ip", reqVO.getUserIp())
                .likeIfPresent("username", reqVO.getUsername())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime());
        if (Boolean.TRUE.equals(reqVO.getStatus())) {
            query.eq("result", SysLoginResultEnum.SUCCESS.getResult());
        } else if (Boolean.FALSE.equals(reqVO.getStatus())) {
            query.gt("result", SysLoginResultEnum.SUCCESS.getResult());
        }
        // 降序
        query.orderByDesc("id");
        return selectPage(reqVO, query);
    }
}
