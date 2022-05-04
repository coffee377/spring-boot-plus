package com.voc.restful.security.autoconfigure;

import com.voc.restful.security.ResourceServerProperties;
import com.voc.restful.security.config.BeanConfiguration;
import com.voc.restful.security.config.ResourceServerConfiguration;
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
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@ConditionalOnClass(DefaultAuthenticationEventPublisher.class)
@EnableConfigurationProperties({ResourceServerProperties.class})
@Import({
        BeanConfiguration.class,
        ResourceServerConfiguration.class
}
)
public class ResourceServerAutoConfiguration {
}
