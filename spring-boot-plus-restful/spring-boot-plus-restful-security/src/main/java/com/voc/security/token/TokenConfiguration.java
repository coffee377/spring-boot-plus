package com.voc.security.token;

import com.voc.security.token.jwt.JwtConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/16 11:06
 */
@Import(JwtConfiguration.class)
@EnableConfigurationProperties(TokenProperties.class)
public class TokenConfiguration {

    @Bean
    @ConditionalOnMissingBean
    TokenCache tokenCache(TokenProperties tokenProperties,
                          RedisCacheConfiguration redisCacheConfiguration) {
        return new TokenCache(tokenProperties, redisCacheConfiguration);
    }

    @Bean
    @ConditionalOnMissingBean
    TokenStorage tokenStorage(@Qualifier("redisTemplate") RedisOperations<String, Object> redisOperations,
                              TokenProperties tokenProperties) {
        return new RedisCacheTokenStorage(redisOperations, tokenProperties);
    }

    /**
     * 覆盖默认的 TokenResolver
     * token 支持从参数 access_token 获取
     *
     * @return BearerTokenResolver
     */
    @Bean
    @ConditionalOnClass
    BearerTokenResolver bearerTokenResolver(TokenProperties token) {
        DefaultBearerTokenResolver tokenResolver = new DefaultBearerTokenResolver();
        tokenResolver.setAllowUriQueryParameter(token.isAllowUriQueryParameter());
        tokenResolver.setAllowFormEncodedBodyParameter(token.isAllowFormEncodedBodyParameter());
//        tokenResolver.setBearerTokenHeaderName(token.getBearerTokenHeaderName());
        return tokenResolver;
    }

}
