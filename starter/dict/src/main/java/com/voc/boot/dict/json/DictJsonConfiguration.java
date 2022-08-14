package com.voc.boot.dict.json;

import com.voc.boot.dict.json.jackson.DictItemSerializeProperties;
import com.voc.boot.dict.json.jackson.DictJackson2ObjectMapperBuilder;
import com.voc.boot.dict.json.jackson.DictModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/14 20:13
 */
@Configuration
@EnableConfigurationProperties(DictItemSerializeProperties.class)
public class DictJsonConfiguration {

    /**
     * 数据字典序列化自定义处理
     *
     * @return DictJackson2ObjectMapperBuilder
     */
    @Bean
    @ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
    DictJackson2ObjectMapperBuilder dictJackson2ObjectMapperBuilder() {
        return new DictJackson2ObjectMapperBuilder();
    }

    @Bean
    @ConditionalOnMissingBean
    DictModule dictModule() {
        return new DictModule();
    }
//    @Bean
//    DictItemSerializer dictItemSerializer(DictItemSerializeProperties serializeProperties) {
//        return new DictItemSerializer(serializeProperties);
//    }
}
