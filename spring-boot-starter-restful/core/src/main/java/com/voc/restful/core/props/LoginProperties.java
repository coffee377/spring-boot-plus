package com.voc.restful.core.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/04 21:15
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "api.login")
public class LoginProperties {

    private String page;

    private String processUrl;

}
