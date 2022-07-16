package com.voc.oss.aliyun;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 18:57
 */
@ConditionalOnProperty(prefix = "oss.aliyun", name = "active", havingValue = "true")
@EnableConfigurationProperties(AliyunProperties.class)
public class AliyunConfiguration {
}
