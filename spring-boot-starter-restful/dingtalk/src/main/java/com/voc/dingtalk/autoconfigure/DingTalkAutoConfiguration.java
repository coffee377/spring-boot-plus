package com.voc.dingtalk.autoconfigure;

import com.voc.restful.core.autoconfigure.cache.RedisCacheAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

}
