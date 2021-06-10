package com.voc.dingtalk.properties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 16:43
 */
@Getter
@Setter
@EqualsAndHashCode
public class App {

    /**
     * 应用名称
     */
    private String name;

    private String agentId;

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
}
