package com.voc.api.config.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Jackson 配置类 对Json数据进行特殊处理
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 09:20
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ObjectMapper.class)
@ConditionalOnProperty(
        prefix = "api.json",
        name = "type",
        havingValue = "jackson",
        matchIfMissing = true
)
@EnableConfigurationProperties({JsonProperties.class})
public class JacksonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(DateJsonSerializer.class)
    DateJsonSerializer dateJsonSerializer(JsonProperties jsonProperties) {
        return new DateJsonSerializer(jsonProperties);
    }

    @Bean
    @ConditionalOnMissingBean(CustomBeanSerializerModifier.class)
    CustomBeanSerializerModifier customBeanSerializerModifier(DateJsonSerializer dateJsonSerializer) {
        return new CustomBeanSerializerModifier(dateJsonSerializer);
    }

    /**
     * jackson 配置
     *
     * @param builder                      Jackson2ObjectMapperBuilder
     * @param customBeanSerializerModifier CustomBeanSerializerModifier
     * @return ObjectMapper
     */
    @Bean
    @Primary
    @ConditionalOnClass({Jackson2ObjectMapperBuilder.class, CustomBeanSerializerModifier.class})
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder, CustomBeanSerializerModifier customBeanSerializerModifier) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        /* 不序列化值为空的属性(包括空对象、空字符串、集合长度为0) */
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        SerializerFactory serializerFactory = objectMapper.getSerializerFactory().withSerializerModifier(customBeanSerializerModifier);
        objectMapper.setSerializerFactory(serializerFactory);
        return objectMapper;
    }

}
