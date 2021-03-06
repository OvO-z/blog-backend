package top.ooovo.blog.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author QAQ
 * @date 2021/12/31
 */

@SuppressWarnings("SpringComponentScan")
@SpringBootApplication(scanBasePackages = {"${ovo.info.base-package}"})
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
