package com.voc.security.oauth2.entity;

import com.voc.security.oauth2.enums.AuthorizationGrantType;
import com.voc.security.oauth2.enums.ClientAuthenticationMethod;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/11 17:20
 */
@Data
@Builder
public class OAuth2Client {

    /**
     * 主键 ID
     */
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

    private OAuth2ClientSettings oauth2ClientSettings;
    private OAuth2TokenSettings oauth2TokenSettings;

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

        RegisteredClient.Builder builder = RegisteredClient.withId(Optional.ofNullable(this.id).orElse(UUID.randomUUID().toString()))
                .clientId(Optional.ofNullable(clientId).orElse(UUID.randomUUID().toString()))
                .clientSecret(clientSecret)
                .clientIdIssuedAt(clientIdIssuedAt)
                .clientSecretExpiresAt(clientSecretExpiresAt)
                .clientName(clientName)
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
                .scopes(scopesConsumer -> scopesConsumer.addAll(scopes))
                .clientSettings(oauth2ClientSettings.toClientSettings())
                .tokenSettings(oauth2TokenSettings.toTokenSettings());
        return builder.build();
    }

    public static OAuth2Client fromRegisteredClient(RegisteredClient registeredClient) {
        return null;
    }

}
