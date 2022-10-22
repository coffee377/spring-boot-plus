package com.voc.boot.cache;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 23:16
 */
class DefaultCacheTimeToLiveTest {

    @Test
    public void demo() {
        DefaultCacheTimeToLive.Builder builder = DefaultCacheTimeToLive.builder();
        DefaultCacheTimeToLive cacheTimeToLive = builder.name("test").time(Duration.ZERO).build();
        assertEquals("test", cacheTimeToLive.getName());
        assertEquals(Duration.ZERO, cacheTimeToLive.getTime());
    }
}
