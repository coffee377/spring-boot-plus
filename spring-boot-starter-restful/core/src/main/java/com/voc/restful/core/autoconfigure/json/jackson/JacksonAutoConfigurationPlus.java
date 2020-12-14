package com.voc.restful.core.autoconfigure.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/29 08:58
 */
@Order()
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureAfter(JacksonAutoConfiguration.class)
@ConditionalOnProperty(
        prefix = "api.json",
        name = "type",
        havingValue = "jackson",
        matchIfMissing = true
)
@ComponentScan(
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Component.class)
        }
)
@EnableConfigurationProperties({JsonProperties.class, JacksonProperties.class})
public class JacksonAutoConfigurationPlus {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
    static class Jackson2ObjectMapperBuilderCustomizerConfiguration {

        @Bean
        Jackson2ObjectMapperBuilderPlus jackson2ObjectMapperBuilderPlus(ApplicationContext applicationContext,
                                                                        JsonProperties jsonProperties,
                                                                        JacksonProperties jacksonProperties) {
            return new Jackson2ObjectMapperBuilderPlus(applicationContext, jsonProperties, jacksonProperties);
        }

    }

}
