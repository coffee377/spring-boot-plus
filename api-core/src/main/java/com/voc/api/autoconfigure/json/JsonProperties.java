package com.voc.api.autoconfigure.json;

import com.voc.api.autoconfigure.ExceptionResult;
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
     * 启用 json 异常响应
     */
    private ExceptionResult exceptionResult;

    /**
     * json 类型
     */
    private JsonType type = JsonType.JACKSON;

    /**
     * 是否开启 utc 时间戳
     */
    @Deprecated
    private boolean utcInstant = false;

    /**
     * 是否开启 utc 时间戳
     */
    private boolean utcTimestamp;

    /**
     * json 格式
     */
    @NestedConfigurationProperty
    private JsonFormat format = new JsonFormat();

}


