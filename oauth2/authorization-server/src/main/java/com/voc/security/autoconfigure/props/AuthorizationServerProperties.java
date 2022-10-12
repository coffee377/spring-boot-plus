package com.voc.security.autoconfigure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 15:47
 */
@Data
@ConfigurationProperties(prefix = "api.security.auth-server")
public class AuthorizationServerProperties {

    /**
     * 用户名密码登陆处理地址，默认 /login
     */
    private String loginProcessUrl;

    /**
     * 注销用户处理地址，默认 /logout
     */
    private String logoutProcessUrl;

}
