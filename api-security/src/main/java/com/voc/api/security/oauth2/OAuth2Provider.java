package com.voc.api.security.oauth2;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/15 14:15
 */
public enum OAuth2Provider {
    GITHUB {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.BASIC, DEFAULT_REDIRECT_URL);
            builder.scope("read:user");
            builder.authorizationUri("https://github.com/login/oauth/authorize");
            builder.tokenUri("https://github.com/login/oauth/access_token");
            builder.userInfoUri("https://api.github.com/user");
            builder.userNameAttributeName("id");
            builder.clientName("GitHub");
            return builder;
        }
    },
    PASSWORD {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.BASIC, DEFAULT_REDIRECT_URL);
            builder.authorizationGrantType(AuthorizationGrantType.PASSWORD);
            builder.scope("all");
            builder.authorizationUri("http://localhost:8088/oauth2/v1/authorize");
            builder.tokenUri("http://localhost:8088/oauth2/v1/token");
            builder.userInfoUri("http://localhost:8088/oauth2/v1/userinfo");
            builder.userNameAttributeName("id");
            builder.clientName("PASSWORD");
            return builder;
        }
    },
    CLIENT {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.BASIC, DEFAULT_REDIRECT_URL);
            builder.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS);
            builder.scope("all");
            builder.authorizationUri("http://localhost:8088/oauth2/v1/authorize");
            builder.tokenUri("http://localhost:8088/oauth2/v1/token");
            builder.userInfoUri("http://localhost:8088/oauth2/v1/userinfo");
            builder.userNameAttributeName("id");
            builder.clientName("CLIENT");
            return builder;
        }
    },
    ;

    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";

    protected final ClientRegistration.Builder getBuilder(String registrationId,
                                                          ClientAuthenticationMethod method,
                                                          String redirectUri) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUriTemplate(redirectUri);
        return builder;
    }

    /**
     * Create a new
     * {@link org.springframework.security.oauth2.client.registration.ClientRegistration.Builder
     * ClientRegistration.Builder} pre-configured with provider defaults.
     *
     * @param registrationId the registration-id used with the new builder
     * @return a builder instance
     */
    public abstract ClientRegistration.Builder getBuilder(String registrationId);
}
