package com.voc.boot.cache;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/28 09:54
 * @since 0.1.1
 */
@FunctionalInterface
public interface CacheTimeToLiveCustomizer {

    /**
     * 自定义 CacheTimeToLive
     *
     * @param cacheTimeToLive 缓存时间配置
     */
    void customize(CacheTimeToLive cacheTimeToLive);
}
