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
    String PREFIX = "dingtalk";
    String APP = PREFIX + ":app";
    String ROBOT = PREFIX + ":robot";

    String APP_ACCESS_TOKEN = APP + ":access_token";
    String APP_JS_TICKET = APP + ":jsapi_ticket";

    String APP_SECRET = APP + ":appSecret";
}
