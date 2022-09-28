package com.voc.dingtalk.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 11:04
 */
@Configuration
//@AutoConfigureAfter(RedisCacheAutoConfiguration.class)
@EnableConfigurationProperties(DingTalkProperties.class)
@ComponentScan(basePackages = "com.voc.dingtalk")
@ConditionalOnProperty(prefix = "spring.dingtalk", name = "enable", havingValue = "true", matchIfMissing = true)
public class DingTalkAutoConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
