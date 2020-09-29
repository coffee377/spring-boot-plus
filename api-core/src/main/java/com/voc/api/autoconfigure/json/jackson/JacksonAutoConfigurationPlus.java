package com.voc.api.autoconfigure.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voc.api.autoconfigure.json.JsonProperties;
import com.voc.api.autoconfigure.json.jackson.deser.*;
import com.voc.api.autoconfigure.json.jackson.ser.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/29 08:58
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ObjectMapper.class)
@ConditionalOnProperty(
        prefix = "api.json",
        name = "type",
        havingValue = "jackson",
        matchIfMissing = true
)
public class JacksonAutoConfigurationPlus {

    @Configuration(proxyBeanMethods = false)
    static class SerializerConfiguration {
        @Bean
        public DateSerializerPlus dateSerializerPlus(JsonProperties jsonProperties) {
            return new DateSerializerPlus(jsonProperties);
        }

        @Bean
        public InstantSerializerPlus instantSerializerPlus(JsonProperties jsonProperties) {
            return new InstantSerializerPlus(jsonProperties);
        }

        @Bean
        public LocalDateTimeSerializerPlus localDateTimeSerializerPlus(JsonProperties jsonProperties) {
            return new LocalDateTimeSerializerPlus(jsonProperties);
        }

        @Bean
        public LocalDateSerializerPlus localDateSerializerPlus(JsonProperties jsonProperties) {
            return new LocalDateSerializerPlus(jsonProperties);
        }

        @Bean
        public LocalTimeSerializerPlus localTimeSerializerPlus(JsonProperties jsonProperties) {
            return new LocalTimeSerializerPlus(jsonProperties);
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class DeserializerConfigurationPlus {
        @Bean
        public DateDeserializerPlus dateDeserializerPlus(JsonProperties jsonProperties) {
            return new DateDeserializerPlus(jsonProperties);
        }

        @Bean
        public InstantDeserializerPlus instantDeserializerPlus(JsonProperties jsonProperties) {
            return new InstantDeserializerPlus(jsonProperties);
        }


        @Bean
        public LocalDateTimeDeserializerPlus localDateTimeDeserializerPlus(JsonProperties jsonProperties) {
            return new LocalDateTimeDeserializerPlus(jsonProperties);
        }


        @Bean
        public LocalDateDeserializerPlus localDateDeserializerPlus(JsonProperties jsonProperties) {
            return new LocalDateDeserializerPlus(jsonProperties);
        }

        @Bean
        public LocalTimeDeserializerPlus localTimeDeserializerPlus(JsonProperties jsonProperties) {
            return new LocalTimeDeserializerPlus(jsonProperties);
        }
    }

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
