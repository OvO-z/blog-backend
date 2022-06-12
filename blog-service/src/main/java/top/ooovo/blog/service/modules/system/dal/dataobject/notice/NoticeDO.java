package top.ooovo.blog.service.modules.system.dal.dataobject.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

/**
 * 通知公告表
 * @TableName sys_notice
 */
@TableName(value ="sys_notice")
@Data
public class NoticeDO extends BaseDO implements Serializable {
    /**
     * 公告ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告类型（1通知 2公告）
     */
    private Byte type;

    /**
     * 公告状态（0正常 1关闭）
     */
    private Byte status;

    /**
     * 租户编号
     */
    private Long tenantId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}