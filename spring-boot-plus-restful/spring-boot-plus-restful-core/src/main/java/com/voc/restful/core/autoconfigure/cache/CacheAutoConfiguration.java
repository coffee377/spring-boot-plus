package com.voc.restful.core.autoconfigure.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 17:07
 */
@EnableCaching
@Configuration
@Import({CaffeineCacheAutoConfiguration.class, RedisCacheAutoConfiguration.class})
public class CacheAutoConfiguration {
}
