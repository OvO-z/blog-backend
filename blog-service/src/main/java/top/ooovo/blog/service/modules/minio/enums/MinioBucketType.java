package top.ooovo.blog.service.modules.minio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ovo
 * @time 2022/07/17
 */

@AllArgsConstructor
@Getter
public enum MinioBucketType {

    /**
     * PHOTO: 照片
     * Markdown: 博客文章
     */
    PHOTO(0, "photos", "/photos/"),
    MARKDOWN(1,"markdown", "/markdown/"),;

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 存储桶名
     */
    private final String name;

    /**
     * api 路径
     */

    private final String api;
}
