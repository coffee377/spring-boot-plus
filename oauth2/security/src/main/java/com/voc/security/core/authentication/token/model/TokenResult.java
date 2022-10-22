package com.voc.security.core.authentication.token.model;


import lombok.Data;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/17 17:31
 */
@Data
public class TokenResult {

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    public TokenResult(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenResult(String accessToken) {
        this(accessToken, null);
    }

    public TokenResult() {
    }
}
