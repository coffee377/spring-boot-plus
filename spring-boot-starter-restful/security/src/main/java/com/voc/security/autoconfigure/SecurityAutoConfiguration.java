package com.voc.security.autoconfigure;

import com.voc.security.SecurityProperties;
import com.voc.security.configurer.RestfulLoginConfigurer;
import com.voc.security.configurer.SwitchUserConfigurer;
import com.voc.security.token.TokenConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/14 08:27
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
@Import({SecurityImport.class, WebSecurityConfig.class, RestfulLoginConfigurer.class,
        SwitchUserConfigurer.class, BeanConfig.class,
        TokenConfiguration.class})
public class SecurityAutoConfiguration {
}
