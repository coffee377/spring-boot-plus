package com.voc.security.oauth2.enums;

import com.voc.common.api.dict.FuncEnumDictItem;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 18:49
 *
 * <div>
 *     <a href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/overview.html">spring-authorization-server</a>
 * </div>
 */
public enum OAuth2ClientAuthenticationMethod implements FuncEnumDictItem<Integer> {
    /**
     * <a href="https://datatracker.ietf.org/doc/html/rfc7636">RFC 7636</a>
     */
    NONE("none", "公开客户端", ClientAuthenticationMethod.NONE), // 1
    CLIENT_SECRET_BASIC("client_secret_basic", "Basic 认证", ClientAuthenticationMethod.CLIENT_SECRET_BASIC), // 2
    CLIENT_SECRET_POST("client_secret_post", "POST 认证", ClientAuthenticationMethod.CLIENT_SECRET_POST), // 4
    CLIENT_SECRET_JWT("client_secret_jwt", "客户端密钥签证JWT", ClientAuthenticationMethod.CLIENT_SECRET_JWT), // 8
    PRIVATE_KEY_JWT("private_key_jwt", "私钥签证JWT", ClientAuthenticationMethod.PRIVATE_KEY_JWT), // 16
    ;
    private final String text;
    private final String description;

    private final ClientAuthenticationMethod authenticationMethod;

    OAuth2ClientAuthenticationMethod(String text, String description, ClientAuthenticationMethod authenticationMethod) {
        this.text = text;
        this.description = description;
        this.authenticationMethod = authenticationMethod;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public ClientAuthenticationMethod getAuthenticationMethod() {
        return authenticationMethod;
    }

    @Override
    public Integer apply(BigInteger bigInteger) {
        return bigInteger.intValue();
    }

    public ClientAuthenticationMethod toAuthenticationMethod() {
        return authenticationMethod;
    }
}
