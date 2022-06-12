package top.ooovo.blog.service.modules.system.dal.dataobject.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

/**
 * 字典类型表
 * @TableName sys_dict_type
 */
@TableName(value ="sys_dict_type")
@Data
public class DictTypeDO extends BaseDO implements Serializable {
    /**
     * 字典主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}