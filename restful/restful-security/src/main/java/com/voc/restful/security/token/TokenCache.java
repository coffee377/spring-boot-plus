//package com.voc.security.token;
//
//import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//
//import java.time.Duration;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/06/17 16:40
// */
//public class TokenCache implements RedisCacheManagerBuilderCustomizer {
//
//    private final TokenProperties tokenProperties;
//    private final RedisCacheConfiguration redisCacheConfiguration;
//
//    public TokenCache(TokenProperties tokenProperties, RedisCacheConfiguration redisCacheConfiguration) {
//        this.tokenProperties = tokenProperties;
//        this.redisCacheConfiguration = redisCacheConfiguration;
//    }
//
//    @Override
//    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
//        Duration accessTokenExpiresIn = tokenProperties.getAccessTokenExpiresIn();
//        builder.withCacheConfiguration(RedisCacheTokenStorage.ACCESS_TOKEN_CACHE, redisCacheConfiguration.entryTtl(accessTokenExpiresIn));
//
//        Duration refreshTokenExpiresIn = tokenProperties.getRefreshTokenExpiresIn();
//        builder.withCacheConfiguration(RedisCacheTokenStorage.REFRESH_TOKEN_CACHE, redisCacheConfiguration.entryTtl(refreshTokenExpiresIn));
//    }
//}
