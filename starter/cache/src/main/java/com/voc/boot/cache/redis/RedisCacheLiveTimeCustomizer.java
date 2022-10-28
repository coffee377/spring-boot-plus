package com.voc.boot.cache.redis;

import com.voc.boot.cache.CacheTimeToLive;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.List;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 22:29
 */
public class RedisCacheLiveTimeCustomizer implements RedisCacheManagerBuilderCustomizer {

    private final RedisCacheConfiguration cacheConfiguration;

    private final List<CacheTimeToLive> timeToLives;

    public RedisCacheLiveTimeCustomizer(List<CacheTimeToLive> cacheTTLS, RedisCacheConfiguration cacheConfiguration) {
        this.timeToLives = cacheTTLS;
        if (cacheConfiguration == null) {
            this.cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        } else {
            this.cacheConfiguration = cacheConfiguration;
        }
    }

    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        timeToLives.forEach(ttl ->
                builder.withCacheConfiguration(ttl.getName(), cacheConfiguration.entryTtl(ttl.getTime()))
        );
    }

}
