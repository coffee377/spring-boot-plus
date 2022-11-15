package com.voc.security.oauth2.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.api.entity.impl.BasePersistEntity;
import com.voc.security.oauth2.enums.OAuth2AuthorizationGrantType;
import com.voc.security.oauth2.enums.OAuth2ClientAuthenticationMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/11 17:20
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oauth2_client")
public class OAuth2Client extends BasePersistEntity<String> {

    /**
     * 注册 ID
     */
    private String registrationId;

    /**
     * 认证提供商
     */
    private String provider;

    /**
     * 客户端 ID
     */
    private String clientId;

    /**
     * 客户端注册时间
     */
    private Instant clientIdIssuedAt;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 客户端密钥过期时间
     */
    private Instant clientSecretExpiresAt;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 客户端认证方法
     */
    private Set<OAuth2ClientAuthenticationMethod> clientAuthenticationMethods;

    /**
     * 授权类型
     */
    private Set<OAuth2AuthorizationGrantType> authorizationGrantTypes;

    /**
     * 重定向地址
     */
    private Set<String> redirectUris;

    /**
     * 允许授予的权限
     */
    private Set<String> scopes;

    /**
     * 客户端设置
     */
    private OAuth2ClientSettings clientSettings;

    /**
     * 令牌设置
     */
    private OAuth2TokenSettings tokenSettings;

    /**
     * To RegisteredClient.
     *
     * @return the registered client
     */
    public RegisteredClient toRegisteredClient() {
        RegisteredClient.Builder builder = RegisteredClient
                .withId(Optional.ofNullable(id).orElse(UUID.randomUUID().toString()))
                .clientId(Optional.ofNullable(clientId).orElse(UUID.randomUUID().toString()))
                .clientSecret(clientSecret)
                .clientIdIssuedAt(clientIdIssuedAt)
                .clientSecretExpiresAt(clientSecretExpiresAt)
                .clientName(clientName)
                .clientAuthenticationMethods(methodsConsumer -> {
                    List<ClientAuthenticationMethod> collect = Optional.ofNullable(clientAuthenticationMethods)
                            .orElse(Collections.emptySet()).stream()
                            .map(OAuth2ClientAuthenticationMethod::toAuthenticationMethod).collect(Collectors.toList());
                    methodsConsumer.addAll(collect);
                })
                .authorizationGrantTypes(typesConsumer -> {
                    List<AuthorizationGrantType> collect = Optional.ofNullable(authorizationGrantTypes)
                            .orElse(Collections.emptySet()).stream()
                            .map(OAuth2AuthorizationGrantType::toGrantType).collect(Collectors.toList());
                    typesConsumer.addAll(collect);
                })
                .redirectUris(redirectsConsumer -> {
                    Set<String> set = Optional.ofNullable(redirectUris).orElse(Collections.emptySet());
                    redirectsConsumer.addAll(set);
                })
                .scopes(scopesConsumer -> {
                    Set<String> set = Optional.ofNullable(scopes).orElse(Collections.emptySet());
                    scopesConsumer.addAll(set);
                })
//                .scope(OidcScopes.OPENID)
                .clientSettings(clientSettings.toClientSettings())
                .tokenSettings(tokenSettings.toTokenSettings());

        return builder.build();
    }

    public static OAuth2Client from(RegisteredClient registeredClient) {
        Set<OAuth2ClientAuthenticationMethod> methods = Arrays.stream(OAuth2ClientAuthenticationMethod.values())
                .filter(method -> registeredClient.getClientAuthenticationMethods().contains(method.getAuthenticationMethod())).collect(Collectors.toSet());
        Set<OAuth2AuthorizationGrantType> grantTypes = Arrays.stream(OAuth2AuthorizationGrantType.values())
                .filter(type -> registeredClient.getAuthorizationGrantTypes().contains(type.getGrantType())).collect(Collectors.toSet());
        OAuth2ClientBuilder builder = OAuth2Client.builder()
                .id(registeredClient.getId())
                .registrationId("")
                .provider("")
                .clientId(registeredClient.getClientId())
                .clientIdIssuedAt(registeredClient.getClientIdIssuedAt())
                .clientSecret(registeredClient.getClientSecret())
                .clientSecretExpiresAt(registeredClient.getClientSecretExpiresAt())
                .clientName(registeredClient.getClientName())
                .clientAuthenticationMethods(methods)
                .authorizationGrantTypes(grantTypes)
                .redirectUris(registeredClient.getRedirectUris())
                .scopes(registeredClient.getScopes())
                .clientSettings(OAuth2ClientSettings.from(registeredClient.getClientSettings()))
                .tokenSettings(OAuth2TokenSettings.from(registeredClient.getTokenSettings()));
        return builder.build();
    }

}
