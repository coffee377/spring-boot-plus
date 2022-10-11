package com.voc.oauth2.entity;

import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 18:59
 */
@Getter
@AllArgsConstructor
public enum AuthorizationGrantType implements EnumDictItem<Integer> {
    PASSWORD(0, "password", "密码模式"),
    AUTHORIZATION_CODE(1, "authorization_code", "授权码模式"),
    CLIENT_CREDENTIALS(2, "client_credentials", "客户端凭证"),
    REFRESH_TOKEN(3, "refresh_token", "刷新令牌"),
    JWT_BEARER(4, "urn:ietf:params:oauth:grant-type:jwt-bearer", "JWT令牌"),
    ;
    private final Integer value;
    private final String text;
    private final String description;


    @Override
    public Integer getMask() {
        return this.value;
    }
}
