package top.ooovo.blog.service.framework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import top.ooovo.framework.web.config.WebProperties;

import javax.annotation.Resource;

/**
 * @author QAQ
 * @date 2021/12/31
 */

@Configuration
public class SecurityConfiguration {

    @Resource
    private WebProperties properties;

    @Bean
    public Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry> authorizeRequestsCustomizer() {
        return registry -> {
            // 通用的接口，可匿名访问
            // TODO 可接入自定义路径
//            registry.antMatchers(api("/system/**")).anonymous();
//            registry.antMatchers(api("/system/**")).anonymous();
        };
    }

    private String api(String url) {
        return properties.getApiPrefix() + url;
    }
}
