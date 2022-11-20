package com.voc.boot.dict.handler;

import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.2
 */
@Configuration
@ConditionalOnMissingClass("com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer")
@ConditionalOnClass({TypeHandler.class, ConfigurationCustomizer.class})
public class MyBatisTypeHandlerConfiguration {

    @Bean
    ConfigurationCustomizer mybatisTypeHandler(Consumer<TypeHandlerRegistry> consumer) {
        return configuration -> consumer.accept(configuration.getTypeHandlerRegistry());
    }

}
