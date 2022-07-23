package com.voc.boot.result.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 12:22
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "api.result")
public class ResultProperties {

    /**
     * 异常响应类型
     */
    private ExceptionType exceptionType;

    /**
     * 响应结果包装配置
     */
    @NestedConfigurationProperty
    private ResultWrapper wrapper = new ResultWrapper();

    /**
     * Result 序列化名称配置
     */
    @NestedConfigurationProperty
    private JsonProperty json = new JsonProperty();

}


