package top.ooovo.blog.service.modules.infra.enums.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * API 异常数据的处理状态
 * @author QAQ
 * @date 2021/11/25
 */

@AllArgsConstructor
@Getter
public enum InfApiErrorLogProcessStatusEnum {

    INIT(0, "未处理"),
    DONE(1, "已处理"),
    IGNORE(2, "已忽略");

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 资源类型名
     */
    private final String name;
}
