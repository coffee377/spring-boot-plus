package com.voc.oss.aliyun;

import com.voc.oss.BaseProperties;
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
@ConfigurationProperties(prefix = "oss.aliyun")
public class AliyunProperties extends BaseProperties {

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 机密密钥
     */
    private String secretKey;
}
