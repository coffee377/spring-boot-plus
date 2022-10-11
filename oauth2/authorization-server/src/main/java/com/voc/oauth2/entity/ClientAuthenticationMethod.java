package com.voc.oauth2.entity;

import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 18:49
 *
 * <div>
 *     <a href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/overview.html">spring-authorization-server</a>
 * </div>
 */
@Getter
@AllArgsConstructor
public enum ClientAuthenticationMethod implements EnumDictItem<Integer> {
    /**
     * <a href="https://datatracker.ietf.org/doc/html/rfc7636">RFC 7636</a>
     */
    NONE(0, "none", "公开客户端"),
    CLIENT_SECRET_BASIC(1, "client_secret_basic", "Basic 认证"),
    CLIENT_SECRET_POST(2, "client_secret_post", "POST 认证"),
    CLIENT_SECRET_JWT(3, "client_secret_jwt", "客户端密钥签证JWT"),
    PRIVATE_KEY_JWT(4, "private_key_jwt", "私钥签证JWT"),
    ;
    private final Integer value;
    private final String text;
    private final String description;

}
