package com.voc.dingtalk.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:15
 */
@Component
public class DingTalkCacheConfig implements RedisCacheManagerBuilderCustomizer {

    private final RedisCacheConfiguration cacheConfiguration;

    public DingTalkCacheConfig(@Autowired(required = false) RedisCacheConfiguration cacheConfiguration) {
        if (cacheConfiguration == null) {
            this.cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        } else {
            this.cacheConfiguration = cacheConfiguration;
        }
    }

    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        Arrays.stream(DingTalkCacheEnum.values()).forEach(
                dingTalkCacheEnum -> {
                    String name = dingTalkCacheEnum.getName();
                    Duration expirationTime = dingTalkCacheEnum.getExpirationTime();
                    builder.withCacheConfiguration(name, cacheConfiguration.entryTtl(expirationTime));
                }
        );
    }
}
