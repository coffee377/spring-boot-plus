package com.voc.dingtalk.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 21:04
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.dingtalk.proxy")
public class Proxy {

    /**
     * 官方接口地址
     */
    private String openApiUrl = "https://oapi.dingtalk.com";

    /**
     * 是否启用钉钉接口代理
     */
    private boolean enable;

    /**
     * 代理地址
     */
    private String url;

}
