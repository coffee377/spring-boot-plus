package com.voc.oauth2.api.model;

import com.voc.oauth2.api.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/23 08:51
 */
@Getter
@AllArgsConstructor
public enum OAuth2Platform implements AuthProvider {
    DINGTALK("dingtalk", "钉钉"),
    ALIPAY("alipay", "支付宝"),
    WECHAT("wechat", "微信"),
    UNKNOWN("", "未知"),
    ;

    private String name;

    private final String description;

    private OAuth2Platform name(String name) {
        this.name = name;
        return this;
    }

    public static OAuth2Platform of(String name) {
        return Arrays.stream(OAuth2Platform.values())
                .filter(item -> item.name.equalsIgnoreCase(name)).findFirst()
                .orElseGet(() -> OAuth2Platform.UNKNOWN.name(name));
    }

}
