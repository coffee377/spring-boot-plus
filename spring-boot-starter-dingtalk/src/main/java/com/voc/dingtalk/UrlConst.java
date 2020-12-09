package com.voc.dingtalk;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:48
 */
@Getter
@AllArgsConstructor
public enum UrlConst {

    /**
     * 获取 access_token
     */
    ACCESS_TOKEN("/gettoken"),

    /**
     * 获取 jsapi_ticket
     */
    JSAPI_TICKET("/get_jsapi_ticket");

    private final String url;

}
