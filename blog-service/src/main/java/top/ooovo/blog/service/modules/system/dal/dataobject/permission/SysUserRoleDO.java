package top.ooovo.blog.service.modules.system.dal.dataobject.permission;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

import java.io.Serializable;

/**
 * 用户和角色关联表
 * @author QAQ
 * @TableName sys_user_role
 */
@TableName(value ="sys_user_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserRoleDO extends BaseDO implements Serializable {
    /**
     * 自增编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}