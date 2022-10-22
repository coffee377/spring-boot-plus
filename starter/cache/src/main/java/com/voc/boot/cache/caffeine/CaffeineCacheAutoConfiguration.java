package com.voc.boot.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 17:07
 */
@Configuration
@EnableConfigurationProperties(CaffeineCacheProperties.class)
@ConditionalOnClass({Caffeine.class, CaffeineCacheManager.class})
public class CaffeineCacheAutoConfiguration implements CacheManagerCustomizer<CaffeineCacheManager> {

    private final CaffeineCacheProperties cacheProperties;

    public CaffeineCacheAutoConfiguration(CaffeineCacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    /**
     * 配置缓存管理器
     */
    @Override
    public void customize(CaffeineCacheManager cacheManager) {
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                // 初始的缓存空间大小
                .initialCapacity(cacheProperties.getInitialCapacity())
                // 缓存的最大条数
                .maximumSize(cacheProperties.getMaximumSize());
        CaffeineCacheProperties.ExpireStrategy expireStrategy = cacheProperties.getExpireStrategy();

        Duration timeToLive = cacheProperties.getTimeToLive();

        if (timeToLive != null) {
            switch (expireStrategy) {
                case AFTER_ACCESS:
                    // 被读或被写后的一段时间后过期
                    caffeine.expireAfterAccess(timeToLive);
                    break;
                case AFTER_WRITE:
                    // 被写入后的一段时间后过期
                    caffeine.expireAfterWrite(timeToLive);
                    break;
                case NONE:
                default:
            }
        }

        cacheManager.setCaffeine(caffeine);
    }
}
