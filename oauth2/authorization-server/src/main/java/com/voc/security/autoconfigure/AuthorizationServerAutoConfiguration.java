package com.voc.security.autoconfigure;

import com.voc.security.autoconfigure.props.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/14 08:27
 */
@Configuration
//@EnableCaching
@ConditionalOnClass(DefaultAuthenticationEventPublisher.class)
@EnableConfigurationProperties({AuthorizationServerProperties.class})
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import({AuthorizationServerImport.class})
public class AuthorizationServerAutoConfiguration {
}
