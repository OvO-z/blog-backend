package top.ooovo.blog.service.modules.infra.dal.dataobject.config;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import top.ooovo.blog.service.modules.infra.enums.config.InfConfigTypeEnum;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

import java.io.Serializable;

@TableName(value ="inf_config")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfConfigDO extends BaseDO implements Serializable {

    /**
     * 参数主键
     */
    @TableId
    private Long id;

    /**
     * 参数分组
     */
    private String group;

    /**
     * 参数类型 {@link InfConfigTypeEnum}
     */
    private Integer type;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数键名
     */
    private String key;

    /**
     * 参数键值
     */
    private String value;

    /**
     * 是否敏感
     */
    private Boolean sensitive;

    /**
     * 备注
     */
    private String remark;
}