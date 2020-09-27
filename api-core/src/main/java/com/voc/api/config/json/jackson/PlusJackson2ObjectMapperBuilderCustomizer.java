package com.voc.api.config.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.voc.api.config.json.JsonProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 09:07
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
@ConditionalOnProperty(
        prefix = "api.json",
        name = "type",
        havingValue = "jackson",
        matchIfMissing = true
)
@EnableConfigurationProperties({JacksonProperties.class, JsonProperties.class})
public class PlusJackson2ObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

    @Resource
    private JacksonProperties jacksonProperties;

    @Resource
    private JsonProperties jsonProperties;

    @Resource
    private DateJsonSerializer dateJsonSerializer;

    @Resource
    private DateJsonDeserializer dateJsonDeserializer;

    @Bean
    @ConditionalOnMissingBean(DateJsonSerializer.class)
    DateJsonSerializer dateJsonSerializer(JsonProperties jsonProperties) {
        return new DateJsonSerializer(jsonProperties);
    }

    @Bean
    @ConditionalOnMissingBean(DateJsonDeserializer.class)
    DateJsonDeserializer dateJsonDeserializer(JsonProperties jsonProperties) {
        return new DateJsonDeserializer(jsonProperties);
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.featuresToEnable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

        if (this.jacksonProperties.getDefaultPropertyInclusion() != null) {
            builder.serializationInclusion(this.jacksonProperties.getDefaultPropertyInclusion());
        } else {
            /* 不序列化值为空的属性(包括空对象、空字符串、集合长度为0) */
            builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        }
        if (this.jacksonProperties.getTimeZone() != null) {
            builder.timeZone(this.jacksonProperties.getTimeZone());
        } else {
            builder.timeZone(TimeZone.getDefault());
        }
        Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>(0);

        serializers.put(Instant.class, dateJsonSerializer);
        serializers.put(LocalDateTime.class, dateJsonSerializer);
        serializers.put(LocalDate.class, dateJsonSerializer);
        serializers.put(LocalTime.class, dateJsonSerializer);
        serializers.put(Date.class, dateJsonSerializer);

//        builder.serializersByType(serializers);

        Map<Class<?>, JsonDeserializer<?>> deserializers = new HashMap<>(0);

        deserializers.put(Instant.class, dateJsonDeserializer);
        deserializers.put(LocalDateTime.class, dateJsonDeserializer);
        deserializers.put(LocalDate.class, dateJsonDeserializer);
        deserializers.put(LocalTime.class, dateJsonDeserializer);
        deserializers.put(Date.class, dateJsonDeserializer);
//        builder.deserializersByType(deserializers);

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
