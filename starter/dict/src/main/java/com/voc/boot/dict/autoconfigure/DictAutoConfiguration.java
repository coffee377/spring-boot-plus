package com.voc.boot.dict.autoconfigure;

import com.voc.boot.dict.DictItemConverterConfiguration;
import com.voc.boot.dict.MyBatisTypeHandlerConfiguration;
import com.voc.boot.dict.json.jackson.DictJackson2ObjectMapperBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/06 22:45
 */
@Configuration
@Import({DictItemConverterConfiguration.class, MyBatisTypeHandlerConfiguration.class})
public class DictAutoConfiguration {

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
}
