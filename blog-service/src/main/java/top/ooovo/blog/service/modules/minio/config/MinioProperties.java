package top.ooovo.blog.service.modules.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ovo
 * @date 2022/07/17
 */
@ConfigurationProperties("ovo.minio")
@Data
public class MinioProperties {
    
    private String endpoint;

    private String accessKey;

    private String secretKey;

}
