package com.voc.restful.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/28 20:22
 */
@Configuration
public class BeanConfiguration {

    @Bean
    @ConditionalOnMissingBean(BearerTokenResolver.class)
    BearerTokenResolver bearerTokenResolver() {
        DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
        bearerTokenResolver.setAllowFormEncodedBodyParameter(true);
        bearerTokenResolver.setAllowUriQueryParameter(true);
        return bearerTokenResolver;
    }

//    @Bean
//    OpaqueTokenIntrospector opaqueTokenIntrospector() throws Exception {
//        return new NimbusOpaqueTokenIntrospector("http://127.0.0.1:9000/oauth2/introspect", new RestTemplate());
//    }

}
