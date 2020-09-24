package com.voc.api.config.json;

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

    private JsonType type = JsonType.JACKSON;

    @NestedConfigurationProperty
    private JsonFormat format = new JsonFormat();

}


