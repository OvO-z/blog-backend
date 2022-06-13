package top.ooovo.blog.service.modules.blog.dal.dataobject.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import top.ooovo.framework.mybatis.core.dataobject.BaseDO;

/**
 * 博客文章表
 * @TableName blog_article
 */
@TableName(value ="blog_article")
@Data
public class ArticleDO extends BaseDO implements Serializable {
    /**
     * 文章ID
     */
    @TableId(type = IdType.ASSIGN_ID)
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
    private Byte type;

    /**
     * 是否置顶
     */
    private Boolean top;

    /**
     * 文章状态
     */
    private Byte status;

    /**
     * 文章浏览量
     */
    private Long view;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}