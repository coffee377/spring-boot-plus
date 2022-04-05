package com.voc.restful.security.autoconfigure;

import com.voc.restful.security.AuthorizationServerProperties;
import com.voc.restful.security.config.AuthorizationServerConfiguration;
import com.voc.restful.security.config.DefaultSecurityConfiguration;
import com.voc.restful.security.config.TokenConfiguration;
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
@ConditionalOnClass(DefaultAuthenticationEventPublisher.class)
@EnableConfigurationProperties({AuthorizationServerProperties.class})
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import({
        DefaultSecurityConfiguration.class,
        AuthorizationServerConfiguration.class,
        TokenConfiguration.class,
        AuthorizationServerImport.class
}
)
public class AuthorizationServerAutoConfiguration {
}
