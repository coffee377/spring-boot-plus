package com.voc.security.autoconfigure;

import com.voc.boot.dict.handler.FuncEnumProvider;
import com.voc.common.api.dict.FuncEnumDictItem;
import com.voc.security.autoconfigure.props.AuthorizationServerProperties;
import com.voc.security.core.autoconfigure.SecurityCoreAutoConfiguration;
import com.voc.security.oauth2.enums.OAuth2AuthorizationGrantType;
import com.voc.security.oauth2.enums.OAuth2ClientAuthenticationMethod;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/14 08:27
 */
@Configuration
@ConditionalOnClass(DefaultAuthenticationEventPublisher.class)
@EnableConfigurationProperties({AuthorizationServerProperties.class})
//@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import({AuthorizationServerImport.class})
@ComponentScan({"com.voc.security", "com.voc.mybatis"})
@MapperScan("com.voc.security.oauth2.dao")
public class AuthorizationServerAutoConfiguration {

    @Bean
    FuncEnumProvider funcEnumProvider() {
        return () -> Arrays.asList(
                OAuth2ClientAuthenticationMethod.class,
                OAuth2AuthorizationGrantType.class
        );

    }
}
