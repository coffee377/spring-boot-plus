package com.voc.security.oauth2.entity;

import lombok.Data;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 01:11
 */
@Data
public class OAuth2TokenSettings {

    /**
     * 临时授权码有效时长
     */
    Duration authorizationCodeTimeToLive = Duration.ofMinutes(5L); // 5min
    /**
     * 访问令牌有效时长
     */
    Duration accessTokenTimeToLive = Duration.ofMinutes(5L); // 5min
    /**
     * 访问令牌类型
     */
    OAuth2TokenFormat accessTokenFormat = OAuth2TokenFormat.SELF_CONTAINED; // SELF_CONTAINED
    /**
     * 是否可以重用访问令牌
     */
    boolean reuseRefreshTokens = true; // true
    /**
     * 访问令牌有效时长
     */
    Duration refreshTokenTimeToLive = Duration.ofMinutes(60L); // 60min
    /**
     * idToken 签名算法
     */
    SignatureAlgorithm idTokenSignatureAlgorithm = SignatureAlgorithm.RS256; // RS256

    public TokenSettings toTokenSettings() {
        TokenSettings.Builder builder = TokenSettings.builder();
        builder.authorizationCodeTimeToLive(authorizationCodeTimeToLive)
                .accessTokenTimeToLive(accessTokenTimeToLive)
                .accessTokenFormat(accessTokenFormat)
                .reuseRefreshTokens(reuseRefreshTokens)
                .refreshTokenTimeToLive(refreshTokenTimeToLive)
                .idTokenSignatureAlgorithm(idTokenSignatureAlgorithm);
        return builder.build();
    }
}
