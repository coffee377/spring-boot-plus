package com.voc.api.autoconfigure.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/29 09:06
 */
@Configuration
@EnableConfigurationProperties(JsonProperties.class)
@ConditionalOnClass({ObjectMapper.class, Gson.class})
public class JsonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    IJson json(ApplicationContext applicationContext, Environment environment) {
        return new DefaultJson(applicationContext, environment);
    }
}
