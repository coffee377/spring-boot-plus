package com.voc.dingtalk.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 19:19
 */
@Getter
@Setter
public class AppInfo {

    /**
     * 应用的唯一标识
     */
    private String appKey;

    /**
     * 应用的密钥
     */
    private String appSecret;

    /**
     * 应用凭证
     */
    private String accessToken;
}
