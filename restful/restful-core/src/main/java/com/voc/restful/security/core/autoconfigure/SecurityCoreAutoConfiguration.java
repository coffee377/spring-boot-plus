package com.voc.restful.security.core.autoconfigure;

import com.voc.restful.security.core.config.CommonBeanConfiguration;
import com.voc.restful.security.core.config.RestfulBeanConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/04 13:11
 */
@Configuration
@ConditionalOnClass(DefaultAuthenticationEventPublisher.class)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import({CommonBeanConfiguration.class, RestfulBeanConfiguration.class, SecurityImport.class,})
public class SecurityCoreAutoConfiguration {
}
