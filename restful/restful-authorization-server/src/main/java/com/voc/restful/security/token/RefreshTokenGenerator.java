package com.voc.restful.security.token;

import org.springframework.lang.Nullable;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/05 21:21
 */
public class RefreshTokenGenerator implements OAuth2TokenGenerator<OAuth2RefreshToken> {
    private StringKeyGenerator refreshTokenGenerator = new TokenStringKeyGenerator();

    public void setRefreshTokenGenerator(StringKeyGenerator refreshTokenGenerator) {
        this.refreshTokenGenerator = refreshTokenGenerator;
    }

    @Nullable
    @Override
    public OAuth2RefreshToken generate(OAuth2TokenContext context) {
        if (!OAuth2TokenType.REFRESH_TOKEN.equals(context.getTokenType())) {
            return null;
        }
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(context.getRegisteredClient().getTokenSettings().getRefreshTokenTimeToLive());
        return new OAuth2RefreshToken(this.refreshTokenGenerator.generateKey(), issuedAt, expiresAt);
    }
}
