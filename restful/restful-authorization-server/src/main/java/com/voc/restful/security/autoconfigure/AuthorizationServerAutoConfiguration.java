package com.voc.restful.security.autoconfigure;

import com.voc.restful.security.AuthorizationServerProperties;
import com.voc.restful.security.config.AuthorizationServerConfiguration;
import com.voc.restful.security.config.DefaultSecurityConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/14 08:27
 */
@Configuration
@EnableConfigurationProperties({AuthorizationServerProperties.class})
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import({
        DefaultSecurityConfiguration.class,
        AuthorizationServerConfiguration.class,
        AuthorizationServerImport.class}
)
public class AuthorizationServerAutoConfiguration {
}
