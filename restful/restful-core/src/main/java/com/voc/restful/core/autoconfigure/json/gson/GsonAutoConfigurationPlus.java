package com.voc.restful.core.autoconfigure.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voc.restful.core.autoconfigure.json.JsonType;
import com.voc.restful.core.autoconfigure.json.annotation.ConditionalOnJsonType;
import com.voc.restful.core.autoconfigure.json.annotation.Temporal;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 14:50
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({Gson.class})
@ConditionalOnJsonType(JsonType.GSON)
@ComponentScan(
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Temporal.class)
        }
)
public class GsonAutoConfigurationPlus {

    public GsonAutoConfigurationPlus() {
    }

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

    @Bean
    @ConditionalOnClass(GsonBuilder.class)
    GsonBuilderCustomizerPlus gsonBuilderCustomizerPlus(ApplicationContext applicationContext) {
        return new GsonBuilderCustomizerPlus(applicationContext);
    }
}
