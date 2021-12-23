package com.voc.restful.core.autoconfigure.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 14:50
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({Gson.class})
@ConditionalOnProperty(
        prefix = "api.json",
        name = "type",
        havingValue = "gson"
)
@ComponentScan(
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Component.class),
        }
)
public class GsonAutoConfigurationPlus {

    @Bean
    @ConditionalOnMissingBean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    @ConditionalOnBean(Gson.class)
    @ConditionalOnMissingBean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(GsonBuilder.class)
    static class GsonBuilderCustomizerConfiguration {

        @Bean
        GsonBuilderCustomizerPlus gsonBuilderCustomizerPlus(ApplicationContext applicationContext) {
            return new GsonBuilderCustomizerPlus(applicationContext);
        }

    }

}
