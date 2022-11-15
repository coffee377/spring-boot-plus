package com.voc.boot.dict.handler;

import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    ConfigurationCustomizer mybatisTypeHandler() {
        return configuration -> {
            TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
            registry.register(EnumDictItemTypeHandler.class);
            registry.register(FuncEnumDictItemTypeHandler.class);
        };
    }

}
