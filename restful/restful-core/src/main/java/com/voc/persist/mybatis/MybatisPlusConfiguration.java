package com.voc.persist.mybatis;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.voc.persist.IAccountInfo;
import com.voc.persist.DefaultAccountInfo;
import com.voc.persist.mybatis.handler.TableFieldMetaObjectHandler;
import com.voc.persist.mybatis.plus.DefaultMybatisPlusProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/17 12:56
 */
@Configuration
@ConditionalOnClass(MybatisPlusProperties.class)
public class MybatisPlusConfiguration {

    /**
     * 默认配置
     *
     * @return MybatisPlusPropertiesCustomizer
     */
    @Bean("defaultMybatisPlusProperties")
    MybatisPlusPropertiesCustomizer defaultMybatisPlusProperties() {
        return new DefaultMybatisPlusProperties();
    }

    /**
     * 注入默认管理员账户信息
     *
     * @return IAccountInfo
     */
    @Bean
    @ConditionalOnMissingBean
    IAccountInfo account() {
        return new DefaultAccountInfo();
    }

    @Bean
    @ConditionalOnMissingBean
    MetaObjectHandler metaObjectHandler(IAccountInfo account) {
        return new TableFieldMetaObjectHandler(account);
    }
}
