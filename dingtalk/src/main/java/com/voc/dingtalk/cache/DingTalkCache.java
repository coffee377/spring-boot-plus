package com.voc.dingtalk.cache;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:24
 */
public interface DingTalkCache {
    String DING_TALK_CACHE_NAME = "dingtalk";

    String ACCESS_TOKEN = DING_TALK_CACHE_NAME + ":access_token";
    String JS_TICKET = DING_TALK_CACHE_NAME + ":jsapi_ticket";
}
