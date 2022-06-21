package com.voc.restful.security.oauth2.entity;

import com.voc.restful.security.oauth2.AuthorizationGrantType;
import com.voc.restful.security.oauth2.ClientAuthenticationMethod;
import lombok.Data;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/12 23:46
 */
@Data
public class OAuth2Client {
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientRegistrationId;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private ClientSettings clientSettings;
    private TokenSettings tokenSettings;

    static class ClientSettings {
        private boolean requireProofKey;
        private boolean requireAuthorizationConsent;
        private String jwkSetUrl;
        private String authenticationSigningAlgorithm;
    }

    static class TokenSettings {
        Duration accessTokenTimeToLive; // 5min
        OAuth2TokenFormat accessTokenFormat; // SELF_CONTAINED
        boolean reuseRefreshTokens; // true
        Duration refreshTokenTimeToLive; // 60min
        SignatureAlgorithm idTokenSignatureAlgorithm; // RS256
    }
}
