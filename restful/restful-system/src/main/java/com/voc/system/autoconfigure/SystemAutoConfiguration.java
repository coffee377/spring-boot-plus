package com.voc.system.autoconfigure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.voc.api.IAccount;
import com.voc.mybatis.handler.TableFieldMetaObjectHandler;
import com.voc.system.SystemProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 17:07
 */

@Configuration
@EnableConfigurationProperties(SystemProperties.class)
@ComponentScan(basePackages = {"com.voc.system"})
@MapperScan("com.voc.system.dao")
@AutoConfigureBefore({SecurityAutoConfiguration.class})
public class SystemAutoConfiguration {

//    @Bean
//    @ConditionalOnMissingBean
//    IUserService userService() {
//        return new UserService();
//    }

    @Bean
    @ConditionalOnMissingBean
    IAccount account() {
        return new IAccount() {
            @Override
            public Serializable getUserId() {
                return "001";
            }

            @Override
            public String getUserName() {
                return "admin";
            }

            @Override
            public Optional getUserInfo() {
                return Optional.empty();
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean
    MetaObjectHandler metaObjectHandler(IAccount account) {
        return new TableFieldMetaObjectHandler(account);
    }

}
