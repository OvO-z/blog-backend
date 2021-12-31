package top.ooovo.blog.service.modules.system.enums.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author QAQ
 * @date 2021/12/1
 */
@Getter
@AllArgsConstructor
public enum SysLoginResultEnum {

    SUCCESS(0), // 成功
    BAD_CREDENTIALS(10), // 账号或密码不正确
    USER_DISABLED(20), // 用户被禁用

    UNKNOWN_ERROR(100), // 未知异常
    ;

    /**
     * 结果
     */
    private final Integer result;
}
