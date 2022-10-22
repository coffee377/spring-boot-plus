package com.voc.boot.oss.minio;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 18:57
 */
@ConditionalOnProperty(prefix = "oss.minio", name = "active", havingValue = "true")
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {
}
