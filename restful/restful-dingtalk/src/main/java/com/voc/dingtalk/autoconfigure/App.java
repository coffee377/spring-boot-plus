package com.voc.dingtalk.autoconfigure;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 16:43
 */
@Data
@EqualsAndHashCode
public class App {
    /**
     * 是否为主应用
     */
    private boolean primary;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用的唯一标识 key
     */
    private String appKey;

    /**
     * 应用的密钥
     */
    private String appSecret;

    /**
     * 事件订阅配置
     */
    private EventSubscription eventSubscription;

    private String agentId;
}
