package com.voc.restful.security;

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
@ConfigurationProperties(prefix = "api.security.auth-server")
public class AuthorizationServerProperties {

    /**
     * 不需要验证权限的地址
     */
    private List<String> ignore;

    /**
     * 用户名密码登陆处理地址，默认 /login
     */
    private String loginProcessUrl;

    /**
     * 注销用户处理地址，默认 /logout
     */
    private String logoutProcessUrl;

}
