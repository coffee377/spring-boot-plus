package com.voc.dingtalk.autoconfigure;

import com.voc.restful.core.autoconfigure.cache.RedisCacheAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 11:04
 */
@Configuration
@AutoConfigureAfter(RedisCacheAutoConfiguration.class)
@ConfigurationPropertiesScan("com.voc.dingtalk.properties")
@ComponentScan(basePackages = "com.voc.dingtalk")
@ConditionalOnProperty(prefix = "spring.dingtalk", name = "enable", havingValue = "true", matchIfMissing = true)
public class DingTalkAutoConfiguration {

//    @Configuration
//    @ComponentScan(basePackages = "com.voc.dingtalk.service", includeFilters = {@ComponentScan.Filter(classes = Service.class)})
//    static class DingTalkServiceConfiguration {
//
//    }
//
//    @Configuration
//    @ComponentScan(basePackages = "com.voc.dingtalk")
//    static class DingTalkControllerConfiguration {
//
//    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(false);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("*");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }
}
