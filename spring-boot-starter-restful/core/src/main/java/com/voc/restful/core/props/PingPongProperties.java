package com.voc.restful.core.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/17 10:07
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "api.ping")
public class PingPongProperties {

    /**
     * 是否启用 Ping 接口
     */
    private Boolean enable;

    /**
     * Ping 接口地址
     */
    private String path;
}
