package com.voc.restful.core.autoconfigure.json;

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

//    /**
//     * 异常响应类型
//     */
//    private ExceptionResult exceptionResult;

    /**
     * <p>响应结果是否自动包装全局控制开关</p>
     * <p>局部控制请使用 {@link com.voc.restful.core.response.ResponseResult} 注解</p>
     *
     * @deprecated use instead
     */
    @Deprecated
    private Boolean automaticWrapped;

    public void setAutomaticWrapped(Boolean automaticWrapped) {
        this.automaticWrapped = automaticWrapped;
        this.wrapper.setEnable(automaticWrapped);
    }

    @NestedConfigurationProperty
    private JsonWrapper wrapper = new JsonWrapper();

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


