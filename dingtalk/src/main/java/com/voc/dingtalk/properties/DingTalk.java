package com.voc.dingtalk.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:11
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.dingtalk")
public class DingTalk {

    /**
     * 是否启用
     */
    Boolean enable;

    /**
     * 企业ID
     */
    String corpId;

    @NestedConfigurationProperty
    Proxy proxy = new Proxy();

    /**
     * 应用标识
     */
    private String agentId;

    private String appKey;

    private String appSecret;
}

