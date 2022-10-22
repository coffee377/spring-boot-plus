package com.voc.boot.cache.autoconfigure;

import com.voc.boot.cache.caffeine.CaffeineCacheAutoConfiguration;
import com.voc.boot.cache.redis.RedisCacheAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 17:07
 */
@EnableCaching
@Import({CaffeineCacheAutoConfiguration.class, RedisCacheAutoConfiguration.class})
public class CacheAutoConfiguration {
}
