package com.voc.security.oauth2.enums;

import com.voc.common.api.dict.FuncEnumDictItem;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.math.BigInteger;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 18:59
 */
public enum OAuth2AuthorizationGrantType implements FuncEnumDictItem<Integer> {
    PASSWORD("password", "密码模式", AuthorizationGrantType.PASSWORD), // 1
    AUTHORIZATION_CODE("authorization_code", "授权码模式", AuthorizationGrantType.AUTHORIZATION_CODE), // 2
    CLIENT_CREDENTIALS("client_credentials", "客户端凭证", AuthorizationGrantType.CLIENT_CREDENTIALS), // 4
    REFRESH_TOKEN("refresh_token", "刷新令牌", AuthorizationGrantType.REFRESH_TOKEN), // 8
    JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer", "JWT令牌", AuthorizationGrantType.JWT_BEARER), // 16
    ;
    private final String text;
    private final String description;
    private final AuthorizationGrantType grantType;

    OAuth2AuthorizationGrantType(String text, String description, AuthorizationGrantType grantType) {
        this.text = text;
        this.description = description;
        this.grantType = grantType;
    }

    @Override
    public String getName() {
        return description;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public AuthorizationGrantType getGrantType() {
        return grantType;
    }

    @Override
    public Integer apply(BigInteger bigInteger) {
        return bigInteger.intValue();
    }

    public AuthorizationGrantType toGrantType() {
        return grantType;
    }
}
