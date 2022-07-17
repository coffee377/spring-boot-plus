package com.voc.dingtalk.cache;

/**
 * 钉钉缓存
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/18 18:24
 */
public interface DingTalkCache {
    /**
     * 钉钉缓存前缀
     */
    String DING_TALK_CACHE_NAME = "dingtalk";
    String DING_TALK_APP = DING_TALK_CACHE_NAME + ":app";

    String DING_TALK_APP_ACCESS_TOKEN = DING_TALK_APP + ":access_token";
    String DING_TALK_APP_JS_TICKET = DING_TALK_APP + ":jsapi_ticket";

    String APP_SECRET = DING_TALK_CACHE_NAME + ":appSecret";
}
