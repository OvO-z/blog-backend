package top.ooovo.blog.service.modules.system.dal.dataobject.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import top.ooovo.framework.common.enums.CommonStatusEnum;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

import java.util.Date;

/**
 * 用户信息表
 * @TableName sys_user
 * @author QAQ
 */
@TableName(value ="sys_user")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDO extends BaseDO {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 备注
     */
    private String remark;

    /**
     * 帐号状态（0正常 1停用）{@link CommonStatusEnum}
     */
    private Integer status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

}