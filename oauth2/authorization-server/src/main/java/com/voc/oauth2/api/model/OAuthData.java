package com.voc.oauth2.api.model;

import com.voc.oauth2.api.AuthProvider;
import lombok.Data;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/11 09:21
 */
@Data
public class OAuthData {

    /**
     * 认证服务提供商
     */
    private AuthProvider provider;

    /**
     * 客户端 ID
     */
    private String clientId;

    /**
     * 临时授权码
     */
    private String code;

    /**
     * 防止重放攻击参数
     */
    private String state;

    /**
     * 是否扫码登录
     */
    private Boolean scan;

}
