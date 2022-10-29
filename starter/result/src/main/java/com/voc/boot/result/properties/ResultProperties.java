package com.voc.boot.result.properties;

import com.voc.boot.result.IResult;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 12:22
 */
@Data
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
    private JsonField json = new JsonField();

    /**
     * 序列化 {@link IResult#getCode()} （必须是 Number 类型字符串）时，是否以 {@link Number} 进行输出
     */
    private boolean codeAsNumber;

}


