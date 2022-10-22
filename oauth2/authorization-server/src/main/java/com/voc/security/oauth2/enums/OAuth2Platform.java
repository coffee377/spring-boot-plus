package com.voc.security.oauth2.enums;

import com.voc.security.oauth2.OAuth2Provider;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/23 08:51
 */
@Getter
public enum OAuth2Platform implements OAuth2Provider {
    DINGTALK("dingtalk", "钉钉"),
    ALIPAY("alipay", "支付宝"),
    WECHAT("wechat", "微信"),
    QQ("qq", "QQ"),
    UNKNOWN("", "未知"),
    ;

    private String name;

    /**
     * 第三方应用 ID
     */
    private String clientId;

    private String unionid;

    private String openid;

    private final String description;

    OAuth2Platform(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private OAuth2Platform name(String name) {
        this.name = name;
        return this;
    }

    public static OAuth2Platform of(String name) {
        return Arrays.stream(OAuth2Platform.values())
                .filter(item -> item.name.equalsIgnoreCase(name)).findFirst()
                .orElseGet(() -> OAuth2Platform.UNKNOWN.name(name));
    }

    @Override
    public String getUnionId() {
        return unionid;
    }

    @Override
    public String getOpenId() {
        return openid;
    }
}
