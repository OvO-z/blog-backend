package top.ooovo.blog.service.modules.infra.enums.logger;

import top.ooovo.framework.common.exception.ErrorCode;

/**
 * Infra 错误码枚举类
 *
 * @author QAQ
 * @date 2021/12/6
 */

public class InfErrorCodeConstants {
    // ========== API 错误日志 1001002000 ==========
    ErrorCode API_ERROR_LOG_NOT_FOUND = new ErrorCode(1001002000, "API 错误日志不存在");
    ErrorCode API_ERROR_LOG_PROCESSED = new ErrorCode(1001002001, "API 错误日志已处理");
}
