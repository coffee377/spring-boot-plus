//package com.voc.security.oauth2;
//
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.core.AuthenticationMethod;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2020/10/15 14:15
// */
//public enum OAuth2Provider {
//    DINGTALK {
//        @Override
//        public ClientRegistration.Builder getBuilder(String registrationId) {
//            ClientRegistration.Builder builder = getBuilder(registrationId,
//                    ClientAuthenticationMethod.POST, DEFAULT_REDIRECT_URL);
//            builder.registrationId(ProviderName.DINGTALK.getRegistrationId());
//            builder.clientName(ProviderName.DINGTALK.getName());
//            builder.scope("snsapi_login");
//            builder.authorizationUri("https://oapi.dingtalk.com/connect/qrconnect");
//            // 钉钉 扫码登录第三方应用 不需要access_token就可以获取用户身份.
//            // 使用钉钉客户端扫码并确认登录您的web系统，在您的系统内获得正在访问用户的钉钉身份，而用户无需输入账户密码。
//            // 注意：此功能与企业自建应用/第三方企业应用无关，只能用扫码登录打开第三方网站，并且不是钉钉内的应用免登，此流程只能做到获取到用户身份（无手机号和企业相关信息）。
//            builder.tokenUri("no_need_token");
//            builder.userInfoUri("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
//            builder.userNameAttributeName("unionid");
//            builder.userInfoAuthenticationMethod(AuthenticationMethod.QUERY);
//            return builder;
//        }
//    },
//
//    GITHUB {
//        @Override
//        public ClientRegistration.Builder getBuilder(String registrationId) {
//            ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.BASIC, DEFAULT_REDIRECT_URL);
//            builder.registrationId(ProviderName.GITHUB.getRegistrationId());
//            builder.clientName(ProviderName.GITHUB.getName());
//            builder.scope("read:user");
//            builder.authorizationUri("https://github.com/login/oauth/authorize");
//            builder.tokenUri("https://github.com/login/oauth/access_token");
//            builder.userInfoUri("https://api.github.com/user");
//            builder.userNameAttributeName("id");
//            return builder;
//        }
//    },
//
//    ;
//
//    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";
//
//    protected final ClientRegistration.Builder getBuilder(String registrationId,
//                                                          ClientAuthenticationMethod method,
//                                                          String redirectUri) {
//        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
//        builder.clientAuthenticationMethod(method);
//        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
//        builder.redirectUriTemplate(redirectUri);
//        return builder;
//    }
//
//    /**
//     * Create a new
//     * {@link org.springframework.security.oauth2.client.registration.ClientRegistration.Builder
//     * ClientRegistration.Builder} pre-configured with provider defaults.
//     *
//     * @param registrationId the registration-id used with the new builder
//     * @return a builder instance
//     */
//    public abstract ClientRegistration.Builder getBuilder(String registrationId);
//}
