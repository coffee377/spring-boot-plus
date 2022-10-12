package com.voc.security.core.autoconfigure;

import com.voc.security.core.SecurityProperties;
import com.voc.security.core.config.CommonBeanConfiguration;
import com.voc.security.core.config.RestfulBeanConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/04 13:11
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@ConditionalOnClass(DefaultAuthenticationEventPublisher.class)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import({CommonBeanConfiguration.class, RestfulBeanConfiguration.class, SecurityImport.class,})
public class SecurityCoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
