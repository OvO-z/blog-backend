package top.ooovo.blog.service.modules.system.dal.dataobject.permission;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

/**
 * 角色信息表
 * @TableName sys_role
 */
@TableName(value ="sys_role", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDO extends BaseDO {
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private Byte dataScope;

    /**
     * 数据范围(指定部门数组)
     */
    private String dataScopeDeptIds;

    /**
     * 角色状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 角色类型
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;
}