package com.voc.dingtalk.autoconfigure.model;

import lombok.Data;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 09:58
 */
@Data
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
