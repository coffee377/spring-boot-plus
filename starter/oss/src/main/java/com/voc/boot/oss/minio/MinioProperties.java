package com.voc.boot.oss.minio;

import com.voc.boot.oss.BaseProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 19:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "oss.minio")
public class MinioProperties extends BaseProperties {

    /**
     * 地区
     */
    private String region;

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 机密密钥
     */
    private String secretKey;
}
