package com.voc.dingtalk.autoconfigure.config;

import com.voc.boot.cache.CacheTimeToLive;
import com.voc.boot.cache.DefaultCacheTimeToLive;
import com.voc.dingtalk.cache.DingTalkCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/28 09:23
 */
@Configuration
public class DingTalkCacheConfiguration {

    /**
     * 钉钉应用缓存时间配置
     *
     * @return CacheTimeToLive
     */
    @Bean
    CacheTimeToLive dingtalkAppCacheTimeToLive() {
        return DefaultCacheTimeToLive.builder()
                .name(DingTalkCache.APP)
                .time(Duration.ofDays(30))
                .build();
    }

    /**
     * 钉钉应用访问令牌缓存时间配置
     *
     * @return CacheTimeToLive
     */
    @Bean
    CacheTimeToLive dingtalkAccessTokenCacheTimeToLive() {
        return DefaultCacheTimeToLive.builder()
                .name(DingTalkCache.APP_ACCESS_TOKEN)
                .time(Duration.ofHours(2).minus(Duration.ofSeconds(30)))
                .build();
    }

    /**
     * 钉钉 JS 签名令牌缓存时间配置
     *
     * @return CacheTimeToLive
     */
    @Bean
    CacheTimeToLive dingtalkJsTicketCacheTimeToLive() {
        return DefaultCacheTimeToLive.builder()
                .name(DingTalkCache.APP_JS_TICKET)
                .time(Duration.ofHours(2).minus(Duration.ofSeconds(30)))
                .build();
    }

}
