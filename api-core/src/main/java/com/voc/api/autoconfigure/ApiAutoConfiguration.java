package com.voc.api.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 */
@Configuration
//@EnableConfigurationProperties({ApiProperties.class})
@ConfigurationPropertiesScan("com.voc.api")
public class ApiAutoConfiguration implements WebMvcConfigurer {

    @Resource
    private ApiProperties api;

//    @Bean
//    @ConditionalOnProperty(prefix = "api.json", name = "exception-result", havingValue = "json", matchIfMissing = true)
//    ErrorController errorController(ErrorAttributes errorAttributes,
//                                    ServerProperties serverProperties) {
//        return new ErrorPlusController(errorAttributes, serverProperties);
//    }

}
