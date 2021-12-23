package com.voc.security.token;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/16 09:21
 */
public class RedisCacheTokenStorage implements TokenStorage {
    private static final String TOKEN_CACHE = "user";
    public static final String ACCESS_TOKEN_CACHE = TOKEN_CACHE + ":access_token";
    public static final String REFRESH_TOKEN_CACHE = TOKEN_CACHE + ":refresh_token";

    private final RedisOperations<String, Object> redisOperations;
    private final TokenProperties tokenProperties;

    public RedisCacheTokenStorage(RedisOperations<String, Object> redisOperations,TokenProperties tokenProperties) {
        this.redisOperations = redisOperations;
        this.tokenProperties = tokenProperties;
    }

    @Override
    @CachePut(cacheNames = ACCESS_TOKEN_CACHE, key = "#uid.concat(':'+#tokenResponse.accessToken.issuedAt.toEpochMilli())")
    public String createAccessToken(String uid, OAuth2AccessTokenResponse tokenResponse) {
        return tokenResponse.getAccessToken().getTokenValue();
    }

    @Override
    @CachePut(cacheNames = REFRESH_TOKEN_CACHE, key = "#uid.concat(':').concat(#tokenResponse.accessToken.issuedAt.toEpochMilli())")
    public String createRefreshToken(String uid, OAuth2AccessTokenResponse tokenResponse) {
        OAuth2RefreshToken refreshToken = tokenResponse.getRefreshToken();
        if (!ObjectUtils.isEmpty(refreshToken) && StringUtils.hasText(refreshToken.getTokenValue())) {
            return refreshToken.getTokenValue();
        }
        return null;
    }

    @Override
    public List<OnlineUser> onlineStatistics() {
        return null;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = ACCESS_TOKEN_CACHE, allEntries = true),
            @CacheEvict(cacheNames = REFRESH_TOKEN_CACHE, allEntries = true)
    })
    public void offlineAll() {

    }

    @Override
    public void offline(String uid) {
        Set<String> keys = new LinkedHashSet<>();
        Set<String> accessTokenKeys = redisOperations.keys(ACCESS_TOKEN_CACHE.concat(":").concat(uid).concat("*"));
        if (!ObjectUtils.isEmpty(accessTokenKeys)) {
            keys.addAll(accessTokenKeys);
        }
        Set<String> refreshTokenKeys = redisOperations.keys(REFRESH_TOKEN_CACHE.concat(":").concat(uid).concat("*"));
        if (!ObjectUtils.isEmpty(refreshTokenKeys)) {
            keys.addAll(refreshTokenKeys);
        }
        redisOperations.delete(keys);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = ACCESS_TOKEN_CACHE, key = "#uid.concat(':').concat(#issuedAt.toEpochMilli())"),
            @CacheEvict(cacheNames = REFRESH_TOKEN_CACHE, key = "#uid.concat(':').concat(#issuedAt.toEpochMilli())")
    })
    public void offline(String uid, Instant issuedAt) {

    }


}
