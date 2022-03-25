package com.voc.restful.core.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/02 16:37
 */
@Getter
@Setter
@ConfigurationProperties(
        prefix = "spring.cache.caffeine"
)
public class CaffeineCacheProperties {
    /**
     * 基于时间策略的缓存存活时间
     */
    private Duration timeToLive;

    /**
     * 初始的缓存空间大小
     */
    private int initialCapacity = 16;

    /**
     * 缓存的最大条数
     */
    private int maximumSize = 1024;

    /**
     * 基于时间的缓存过期策略
     */
    private ExpireStrategy expireStrategy = ExpireStrategy.AFTER_WRITE;

    public enum ExpireStrategy {
        NONE, AFTER_WRITE, AFTER_ACCESS
    }

}
