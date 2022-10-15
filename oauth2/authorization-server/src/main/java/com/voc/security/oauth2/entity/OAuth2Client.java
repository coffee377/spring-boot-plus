package com.voc.security.oauth2.entity;

import com.voc.security.oauth2.enums.AuthorizationGrantType;
import com.voc.security.oauth2.enums.ClientAuthenticationMethod;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/11 17:20
 */
@Data
@Builder
public class OAuth2Client {

    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;

    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    private Set<String> redirectUris;
    private Set<String> scopes;

    private String clientSettings;
    private String tokenSettings;

    /**
     * To RegisteredClient.
     *
     * @return the registered client
     */
    public RegisteredClient toRegisteredClient() {

//        Set<ClientAuthMethod> clientAuthMethods = clientAuthenticationMethods == null ? Collections.emptySet() : clientAuthenticationMethods;
//        Set<OAuth2GrantType> oAuth2GrantTypes = authorizationGrantTypes == null ? Collections.emptySet() : authorizationGrantTypes;
//        Set<RedirectUri> uris = redirectUris == null ? Collections.emptySet() : redirectUris;
//        Set<OAuth2Scope> oAuth2Scopes = scopes == null ? Collections.emptySet() : scopes;
//
//        RegisteredClient.Builder builder = RegisteredClient.withId(Optional.ofNullable(this.id).orElse(UUID.randomUUID().toString()))
//                .clientId(Optional.ofNullable(this.clientId).orElse(UUID.randomUUID().toString()))
//                .clientSecret(this.clientSecret)
//                .clientIdIssuedAt(this.clientIdIssuedAt)
//                .clientSecretExpiresAt(this.clientSecretExpiresAt)
//                .clientName(this.clientName)
//                .clientAuthenticationMethods(clientAuthenticationMethodSet ->
//                        clientAuthenticationMethodSet.addAll(clientAuthMethods.stream()
//                                .map(ClientAuthMethod::toAuthenticationMethod)
//                                .collect(Collectors.toSet())))
//                .authorizationGrantTypes(authorizationGrantTypeSet ->
//                        authorizationGrantTypeSet.addAll(oAuth2GrantTypes.stream()
//                                .map(OAuth2GrantType::toGrantType)
//                                .collect(Collectors.toSet())))
//                .redirectUris(redirectUriSet -> redirectUriSet.addAll(uris.stream()
//                        .map(RedirectUri::getRedirectUri)
//                        .collect(Collectors.toSet())))
//                .scopes(scopeSet -> scopeSet.addAll(oAuth2Scopes.stream()
//                        .map(OAuth2Scope::getScope)
//                        .collect(Collectors.toSet())))
//                .scope(OidcScopes.OPENID)
//                .clientSettings(this.clientSettings.toClientSettings())
//                .tokenSettings(this.tokenSettings.toTokenSettings());
//        return builder.build();
        return null;
    }

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
