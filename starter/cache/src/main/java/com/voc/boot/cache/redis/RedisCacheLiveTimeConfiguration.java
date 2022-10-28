package com.voc.boot.cache.redis;

import com.voc.boot.cache.CacheTimeToLive;
import com.voc.boot.cache.CacheTimeToLiveCustomizer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 23:39
 */
@Configuration
public class RedisCacheLiveTimeConfiguration {


    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(ObjectProvider<CacheTimeToLive> cacheTimeToLives,
                                                                          ObjectProvider<CacheTimeToLiveCustomizer> cacheTimeToLiveCustomizers,
                                                                          @Autowired(required = false) RedisCacheConfiguration cacheConfiguration) {
        List<CacheTimeToLive> collect = cacheTimeToLives.orderedStream()
                .peek(cacheTimeToLive ->
                        cacheTimeToLiveCustomizers.orderedStream().forEach(
                                customizer ->
                                        customizer.customize(cacheTimeToLive)
                        )
                )
                .collect(Collectors.toList());
        return new RedisCacheLiveTimeCustomizer(collect, cacheConfiguration);
    }

}
