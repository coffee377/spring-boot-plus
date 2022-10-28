package com.voc.dingtalk.autoconfigure.config;

import com.voc.dingtalk.props.ProxyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/27 21:48
 */
@Configuration
@EnableConfigurationProperties(ProxyProperties.class)
public class DingTalkProxyConfiguration {
}
