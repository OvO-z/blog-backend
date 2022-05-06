package top.ooovo.blog.service.modules.infra.enums.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author QAQ
 * @date 2022/5/6
 */

@Getter
@AllArgsConstructor
public enum InfConfigTypeEnum {

    /**
     * 系统配置
     */
    SYSTEM(1),
    /**
     * 自定义配置
     */
    CUSTOM(2);

    private final Integer type;
}
