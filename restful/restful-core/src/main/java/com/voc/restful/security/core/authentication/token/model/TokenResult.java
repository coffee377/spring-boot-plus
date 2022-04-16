package com.voc.restful.security.core.authentication.token.model;


import com.voc.restful.core.entity.BaseJsonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/17 17:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public  class TokenResult extends BaseJsonEntity {

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
