package com.voc.boot.cache;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 23:13
 */
@Data
@Builder(builderClassName = "Builder")
public class DefaultCacheTimeToLive implements CacheTimeToLive {

    /**
     * 缓存名称
     */
    private String name;

    /**
     * 缓存过期时间
     */
    private Duration time;

}
