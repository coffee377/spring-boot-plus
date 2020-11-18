package com.voc.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;

import java.lang.annotation.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/16 16:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({OAuth2AuthorizationServerBeanConfiguration.class, OAuth2AuthorizationServerConfiguration.class})
@Configuration
public @interface EnableAuthorizationServer {

}
