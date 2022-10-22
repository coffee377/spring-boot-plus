package com.voc.boot.oss.autoconfigure;

import com.voc.boot.oss.OSSProperties;
import com.voc.boot.oss.local.LocalFileSystemConfiguration;
import com.voc.boot.oss.aliyun.AliyunConfiguration;
import com.voc.boot.oss.minio.MinioConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 18:49
 */
@EnableConfigurationProperties(OSSProperties.class)
@Import({LocalFileSystemConfiguration.class, MinioConfiguration.class, AliyunConfiguration.class})
public class OSSAutoConfiguration {
}
