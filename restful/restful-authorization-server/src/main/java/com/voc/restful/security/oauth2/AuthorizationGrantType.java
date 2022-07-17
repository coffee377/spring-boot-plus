package com.voc.restful.security.oauth2;

import com.voc.common.dict.enumeration.EnumDictItem;
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
    PASSWORD(0,"密码模式"),
    AUTHORIZATION_CODE(1,"授权码模式"),
    CLIENT_CREDENTIALS(2,"客户端凭证"),
    REFRESH_TOKEN(3,"刷新令牌"),
    JWT_BEARER(4,"JWT令牌"),
    ;
    private final Integer value;
    private final String text;
}
