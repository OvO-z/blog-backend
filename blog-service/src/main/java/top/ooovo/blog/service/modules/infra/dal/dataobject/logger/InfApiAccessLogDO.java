package top.ooovo.blog.service.modules.infra.dal.dataobject.logger;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
 * API 访问日志表
 * @TableName inf_api_access_log
 */
@TableName(value ="inf_api_access_log")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfApiAccessLogDO extends BaseDO implements Serializable {
    /**
     * 日志主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 链路追踪编号
     */
    private String traceId;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户类型
     */
    private Byte userType;

    /**
     * 应用名
     */
    private String applicationName;

    /**
     * 请求方法名
     */
    private String requestMethod;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 用户 IP
     */
    private String userIp;

    /**
     * 浏览器 UA
     */
    private String userAgent;

    /**
     * 开始请求时间
     */
    private Date beginTime;

    /**
     * 结束请求时间
     */
    private Date endTime;

    /**
     * 执行时长
     */
    private Integer duration;

    /**
     * 结果码
     */
    private Integer resultCode;

    /**
     * 结果提示
     */
    private String resultMsg;
}