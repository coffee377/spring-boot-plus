package com.voc.boot.dict.handler;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.voc.common.api.dict.FuncEnumDictItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Map;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.2
 */
@Slf4j
@Configuration
@ConditionalOnClass({TypeHandler.class, ConfigurationCustomizer.class})
@ConditionalOnMissingClass("org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer")
public class MyBatisPlusTypeHandlerConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, FuncEnumDictItem> beansOfType = applicationContext.getBeansOfType(FuncEnumDictItem.class);
        log.warn("{}", beansOfType);
    }

    @Bean
    ConfigurationCustomizer mybatisPlusTypeHandler() {
        return configuration -> {
            TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
            registry.register(EnumDictItemTypeHandler.class);
            registry.register(FuncEnumDictItemTypeHandler.class);
        };
    }


}
