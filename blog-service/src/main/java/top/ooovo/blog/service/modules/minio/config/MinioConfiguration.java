package top.ooovo.blog.service.modules.minio.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author ovo
 * @time 2022/07/17
 */

@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {

    @Resource MinioProperties properties;

    @Bean
    public MinioProperties minioProperties() {
        return new MinioProperties();
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }

}
