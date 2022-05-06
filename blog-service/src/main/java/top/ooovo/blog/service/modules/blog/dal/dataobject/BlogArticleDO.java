package top.ooovo.blog.service.modules.blog.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

import java.io.Serializable;

/**
 * 博客文章表
 * @TableName blog_article
 */
@TableName(value ="blog_article")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogArticleDO extends BaseDO implements Serializable {

    /**
     * 文章ID
     */
    @TableId
    private Long id;

    /**
     * 文章编号
     */
    private String code;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 文章缩略图
     */
    private String cover;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 是否置顶
     */
    private Boolean top;

    /**
     * 文章状态
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}