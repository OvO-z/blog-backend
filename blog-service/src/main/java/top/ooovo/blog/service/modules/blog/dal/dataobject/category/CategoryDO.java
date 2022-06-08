package top.ooovo.blog.service.modules.blog.dal.dataobject.category;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

import java.io.Serializable;

/**
 * 博客分类表
 * @TableName blog_category
 */
@TableName(value ="blog_category")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDO extends BaseDO implements Serializable {
    /**
     * 分类ID
     */
    @TableId
    private Long id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 文章总数
     */
    private Integer count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}