package com.voc.dingtalk.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 09:58
 */
@Getter
@Setter
public class EventSubscription {
    /**
     * 加密 aes_key
     */
    private String aesKey;

    /**
     * 签名 token
     */
    private String token;

    /**
     * 接收事件订阅的url，必须是公网可以访问的url地址
     */
    private String url;
}
