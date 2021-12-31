package top.ooovo.blog.service.modules.infra.dal.dataobject.logger;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

import java.util.Date;

/**
 * 系统异常日志
 * @TableName inf_api_error_log
 * @author QAQ
 */
@TableName(value ="inf_api_error_log")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfApiErrorLogDO extends BaseDO {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 链路追踪编号
     *
     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。
     */
    private String traceId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 应用名
     *
     * 目前读取 spring.application.name
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
     * 异常发生时间
     */
    private Date exceptionTime;

    /**
     * 异常名
     *
     * {@link Throwable#getClass()} 的类全名
     */
    private String exceptionName;

    /**
     * 异常导致的消息
     */
    private String exceptionMessage;

    /**
     * 异常导致的根消息
     */
    private String exceptionRootCauseMessage;

    /**
     * 异常的栈轨迹
     */
    private String exceptionStackTrace;

    /**
     * 异常发生的类全名
     *
     * {@link StackTraceElement#getClassName()}
     */
    private String exceptionClassName;

    /**
     * 异常发生的类文件
     *
     * {@link StackTraceElement#getFileName()}
     */
    private String exceptionFileName;

    /**
     * 异常发生的方法名
     *
     * {@link StackTraceElement#getMethodName()}
     */
    private String exceptionMethodName;

    /**
     * 异常发生的方法所在行
     *
     * {@link StackTraceElement#getLineNumber()}
     */
    private Integer exceptionLineNumber;

    /**
     * 处理状态
     */
    private Integer processStatus;

    /**
     * 处理时间
     */
    private Date processTime;

    /**
     * 处理用户编号
     */
    private Long processUserId;
}