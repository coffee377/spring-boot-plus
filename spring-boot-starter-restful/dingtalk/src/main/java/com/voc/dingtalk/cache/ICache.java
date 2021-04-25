package com.voc.dingtalk.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.time.Duration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:16
 */
public interface ICache extends InitializingBean {

    /**
     * 缓存名称
     *
     * @return String
     */
    String getName();

    /**
     * 过期时间
     *
     * @return Duration
     */
    Duration getExpirationTime();

    /**
     * 初始化完成后
     *
     * @throws Exception 异常
     */
    @Override
    default void afterPropertiesSet() throws Exception {
        Assert.hasLength(this.getName(), "A name of cache is required");
        Assert.notNull(this.getExpirationTime(), "A expiration time must be set");
    }
}
