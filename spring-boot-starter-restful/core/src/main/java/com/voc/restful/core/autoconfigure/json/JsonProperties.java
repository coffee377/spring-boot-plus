package com.voc.restful.core.autoconfigure.json;

import com.voc.restful.core.response.ExceptionResult;
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
@ConfigurationProperties(prefix = "api.json")
public class JsonProperties {

    /**
     * 异常响应类型
     */
    private ExceptionResult exceptionResult;

    /**
     * 响应结果是否自动包装全局控制开关
     * 局部控制请使用 ResponseResult 注解
     *
     * @see com.voc.restful.core.response.ResponseResult
     */
    private Boolean automaticWrapped;

    /**
     * json 类型
     */
    private JsonType type = JsonType.JACKSON;

    /**
     * 是否开启 utc 时间戳
     */
    private boolean utcTimestamp;

    /**
     * json 格式
     */
    @NestedConfigurationProperty
    private JsonFormat format = new JsonFormat();

    /**
     * Result 序列化名称配置
     */
    @NestedConfigurationProperty
    private ResultFieldName result = new ResultFieldName();

}


