package com.voc.security.oauth2.client;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/11 10:48
 */
public class UserInfo {

    /**
     * 用户在第三方平台唯一标识
     */
    private String unionId;

    /**
     * 用户在第三方平台某个应用的唯一标识
     */
    private String openId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;
}
