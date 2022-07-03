package com.voc.dingtalk.cache;

import java.time.Duration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:19
 */
public enum DingTalkCacheEnum implements ICache, DingTalkCache {

    ACCESS_TOKEN(DingTalkCache.DING_TALK_APP_ACCESS_TOKEN, Duration.ofSeconds(6900)),
    JS_TICKET(DingTalkCache.DING_TALK_APP_JS_TICKET, Duration.ofSeconds(6900)),
    APP_INFO(DingTalkCache.DING_TALK_APP, Duration.ofDays(30)),
    APP_SECRET(DingTalkCache.APP_SECRET, Duration.ofDays(30));

    private final String name;

    private final Duration expirationTime;

    DingTalkCacheEnum(String name, Duration expirationTime) {
        this.name = name;
        this.expirationTime = expirationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Duration getExpirationTime() {
        return expirationTime;
    }

}
