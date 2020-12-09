package com.voc.api.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 15:47
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "api.security")
public class SecurityProperties {

    /**
     * 不需要验证权限的地址
     */
    private List<String> ignore;

}
