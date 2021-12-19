package com.voc.restful.core.autoconfigure.json;

import com.voc.restful.core.autoconfigure.json.gson.GsonAutoConfigurationPlus;
import com.voc.restful.core.autoconfigure.json.jackson.JacksonAutoConfigurationPlus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/29 09:06
 */
@Configuration
@Import({JacksonAutoConfigurationPlus.class, GsonAutoConfigurationPlus.class})
@EnableConfigurationProperties({JsonProperties.class})
public class JsonAutoConfiguration {

    @Bean("json")
    @ConditionalOnMissingBean
    IJson json(ApplicationContext applicationContext, Environment environment) {
        return new DefaultJson(applicationContext, environment);
    }
}
