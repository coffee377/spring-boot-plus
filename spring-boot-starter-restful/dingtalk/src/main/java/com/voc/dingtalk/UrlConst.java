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
     * 获取调用企业接口凭证
     */
    GET_TOKEN("/gettoken"),

    /**
     * 获取 jsapi_ticket
     */
    GET_JSAPI_TICKET("/get_jsapi_ticket"),

    /**
     * 通过临时授权码获取用户信息
     */
    SNS_GET_USER_INFO_BY_CODE("/sns/getuserinfo_bycode"),
    TOP_API_USER_GET_BY_UNION_ID("/topapi/user/getbyunionid"),

    TOP_API_V2_USER_GET("/topapi/v2/user/get"),

    ;



    private final String url;

}
