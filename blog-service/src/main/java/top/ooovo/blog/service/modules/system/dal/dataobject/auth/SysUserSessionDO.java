package top.ooovo.blog.service.modules.system.dal.dataobject.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

import java.util.Date;

/**
 * 用户在线 Session
 * @author QAQ
 * @TableName sys_user_session
 */
@TableName(value ="sys_user_session")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class SysUserSessionDO extends BaseDO {
    /**
     * 会话编号
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 会话超时时间
     */
    private Date sessionTimeout;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户 IP
     */
    private String userIp;

    /**
     * 浏览器 UA
     */
    private String userAgent;
}