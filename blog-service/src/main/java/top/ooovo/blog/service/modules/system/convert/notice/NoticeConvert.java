package top.ooovo.blog.service.modules.system.convert.notice;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticeCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticeRespVO;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticeUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.notice.NoticeDO;
import top.ooovo.framework.common.pojo.PageResult;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@Mapper
public interface NoticeConvert {
    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    PageResult<NoticeRespVO> convertPage(PageResult<NoticeDO> page);

    NoticeRespVO convert(NoticeDO bean);

    NoticeDO convert(NoticeUpdateReqVO bean);

    NoticeDO convert(NoticeCreateReqVO bean);
}
