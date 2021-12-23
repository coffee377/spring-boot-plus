package com.voc.security.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/22 14:12
 */
@Getter
@AllArgsConstructor
public enum ProviderName {
    DINGTALK("dingtalk", "DingTalk"),
    ALIPAY("alipay", "AliPay"),
    WECHAT("wechat", "WeChat"),
    GITHUB("github", "GitHub"),

    ;

    private final String registrationId;
    private final String name;

}
