package top.ooovo.blog.service.modules.system.dal.dataobject.logger;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

/**
 * 系统访问记录
 * @author QAQ
 * @TableName sys_login_log
 */
@TableName(value ="sys_login_log")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SysLoginLogDO extends BaseDO {
    /**
     * 访问ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日志类型
     */
    private Long logType;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Byte userType;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 登陆结果
     */
    private Byte result;

    /**
     * 用户 IP
     */
    private String userIp;

    /**
     * 浏览器 UA
     */
    private String userAgent;
}