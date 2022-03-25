package com.voc.restful.core.props;

import com.voc.restful.core.autoconfigure.json.JsonProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 14:51
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    private String prefix = "/api";

    /**
     * json 配置
     */
    @NestedConfigurationProperty
    private JsonProperties json = new JsonProperties();

    /**
     * ping 接口配置
     */
    @NestedConfigurationProperty
    private PingPongProperties ping = new PingPongProperties();

}
