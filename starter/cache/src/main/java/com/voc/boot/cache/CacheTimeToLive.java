package com.voc.boot.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.time.Duration;

/**
 * 缓存过期时间配置
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:16
 * @since 0.1.0
 */
public interface CacheTimeToLive extends InitializingBean {

    /**
     * 缓存名称
     *
     * @return String
     */
    @NonNull
    String getName();

    /**
     * 过期时间
     *
     * @return Duration
     */
    @NonNull
    Duration getTime();

    /**
     * 初始化完成后
     *
     * @throws Exception 异常
     */
    @Override
    default void afterPropertiesSet() throws Exception {
        Assert.hasLength(getName(), "A name of cache is required");
        Assert.notNull(getTime(), "A expiration time must be set");
    }
}
