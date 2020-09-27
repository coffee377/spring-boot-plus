package com.voc.api.config.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voc.api.config.json.JsonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 12:19
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Gson.class)
@ConditionalOnProperty(
        prefix = "api.json",
        name = "type",
        havingValue = "gson"
)
@EnableConfigurationProperties({GsonProperties.class, JsonProperties.class})
public class PlusGsonBuilderCustomizer implements GsonBuilderCustomizer, Ordered {

    @Resource
    private JsonProperties jsonProperties;

    @Resource
    private GsonProperties gsonProperties;

    @Resource
    private DateBean dateBean;

    @Resource
    private InstantBean instantBean;

    @Resource
    private LocalDateTimeBean localDateTimeBean;

    @Resource
    private LocalDateBean localDateBean;

    @Resource
    private LocalTimeBean localTimeBean;

    @Bean
    @ConditionalOnMissingBean
    GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }

    @Override
    public void customize(GsonBuilder builder) {
        builder.registerTypeAdapter(Date.class, dateBean);
        builder.registerTypeAdapter(Instant.class, instantBean);
        builder.registerTypeAdapter(LocalDateTime.class, localDateTimeBean);
        builder.registerTypeAdapter(LocalDate.class, localDateBean);
        builder.registerTypeAdapter(LocalTime.class, localTimeBean);
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
