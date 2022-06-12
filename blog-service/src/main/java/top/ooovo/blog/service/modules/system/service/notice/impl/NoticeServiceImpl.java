package top.ooovo.blog.service.modules.system.service.notice.impl;

import org.springframework.stereotype.Service;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticeCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticePageReqVO;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticeUpdateReqVO;
import top.ooovo.blog.service.modules.system.convert.notice.NoticeConvert;
import top.ooovo.blog.service.modules.system.dal.dataobject.notice.NoticeDO;
import top.ooovo.blog.service.modules.system.dal.mysql.notice.NoticeMapper;
import top.ooovo.blog.service.modules.system.service.notice.NoticeService;
import top.ooovo.framework.common.pojo.PageResult;

import javax.annotation.Resource;

import static top.ooovo.blog.service.modules.system.enums.SysErrorCodeConstants.NOTICE_NOT_FOUND;
import static top.ooovo.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @author QAQ
 * @date 2022/6/12
 */

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Long createNotice(NoticeCreateReqVO reqVO) {
        NoticeDO notice = NoticeConvert.INSTANCE.convert(reqVO);
        noticeMapper.insert(notice);
        return notice.getId();
    }

    @Override
    public void updateNotice(NoticeUpdateReqVO reqVO) {
        // 校验是否存在
        this.checkNoticeExists(reqVO.getId());
        // 更新通知公告
        NoticeDO updateObj = NoticeConvert.INSTANCE.convert(reqVO);
        noticeMapper.updateById(updateObj);
    }

    @Override
    public void deleteNotice(Long id) {
        // 校验是否存在
        this.checkNoticeExists(id);
        // 更新通知公告
        noticeMapper.deleteById(id);
    }

    @Override
    public PageResult<NoticeDO> pageNotices(NoticePageReqVO reqVO) {
        return noticeMapper.selectPage(reqVO);
    }

    @Override
    public NoticeDO getNotice(Long id) {
        return noticeMapper.selectById(id);
    }

    private void checkNoticeExists(Long id) {
        if (id == null) {
            return;
        }
        NoticeDO notice = noticeMapper.selectById(id);
        if (notice == null) {
            throw exception(NOTICE_NOT_FOUND);
        }
    }
}
