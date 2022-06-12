package top.ooovo.blog.service.modules.system.service.notice;

import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticeCreateReqVO;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticePageReqVO;
import top.ooovo.blog.service.modules.system.controller.notice.vo.NoticeUpdateReqVO;
import top.ooovo.blog.service.modules.system.dal.dataobject.notice.NoticeDO;
import top.ooovo.framework.common.pojo.PageResult;

/**
 * 通知公告 Service 接口
 * @author QAQ
 * @date 2022/6/12
 */

public interface NoticeService {

    /**
     * 创建公告
     * @param reqVO 公告信息
     * @return 公告编号
     */
    Long createNotice(NoticeCreateReqVO reqVO);

    /**
     * 更新公告
     * @param reqVO
     */
    void updateNotice(NoticeUpdateReqVO reqVO);

    /**
     * 删除公告
     * @param id
     */
    void deleteNotice(Long id);

    /**
     * 获取公告分页
     * @param reqVO
     * @return
     */
    PageResult<NoticeDO> pageNotices(NoticePageReqVO reqVO);

    /**
     * 获取公告信息
     * @param id
     * @return
     */
    NoticeDO getNotice(Long id);
}
