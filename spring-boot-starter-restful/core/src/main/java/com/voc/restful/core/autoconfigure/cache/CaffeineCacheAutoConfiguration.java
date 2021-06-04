package com.voc.restful.core.autoconfigure.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 17:07
 */
@Configuration
@ConditionalOnClass({Caffeine.class, CaffeineCacheManager.class})
public class CaffeineCacheAutoConfiguration implements CacheManagerCustomizer<CaffeineCacheManager> {

    /**
     * 配置缓存管理器
     */
    @Override
    public void customize(CaffeineCacheManager cacheManager) {
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterWrite(10, TimeUnit.SECONDS)
//                .expireAfterAccess(60, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(1000));
    }
}
