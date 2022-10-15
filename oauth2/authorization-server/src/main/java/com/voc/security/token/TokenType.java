package com.voc.security.token;


import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/02 23:04
 */
public class TokenType {

    public static final OAuth2TokenType ACCESS_TOKEN = OAuth2TokenType.ACCESS_TOKEN;
    public static final OAuth2TokenType REFRESH_TOKEN = OAuth2TokenType.REFRESH_TOKEN;
    /**
     * 临时授权码令牌类型
     */
    public static final OAuth2TokenType AUTHORIZATION_CODE_TOKEN = new OAuth2TokenType("code");
    /**
     * 防止重放攻击令牌类型
     */
    public static final OAuth2TokenType STATE_TOKEN = new OAuth2TokenType("state");
}
