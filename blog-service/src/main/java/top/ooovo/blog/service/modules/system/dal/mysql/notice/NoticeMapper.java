package top.ooovo.blog.service.modules.system.dal.mysql.notice;

import org.apache.ibatis.annotations.Mapper;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticePageReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.notice.NoticeDO;
import top.ooovo.framework.common.pojo.PageResult;
import top.ooovo.framework.mybatis.core.mapper.BaseMapperX;
import top.ooovo.framework.mybatis.core.query.QueryWrapperX;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@Mapper
public interface NoticeMapper extends BaseMapperX<NoticeDO> {
    default PageResult<NoticeDO> selectPage(NoticePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<NoticeDO>()
                .likeIfPresent("title", reqVO.getTitle())
                .eqIfPresent("status", reqVO.getStatus())
                .orderByDesc("id"));
    }
}
