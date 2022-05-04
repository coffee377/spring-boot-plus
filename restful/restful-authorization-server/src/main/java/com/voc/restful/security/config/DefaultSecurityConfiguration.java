package com.voc.restful.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/27 19:22
 */
@Configuration
public class DefaultSecurityConfiguration {
    private final PasswordEncoder passwordEncoder;

    public DefaultSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

//    @Bean("")
//    @Order(10)
//    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests((requests) -> {
//            requests.anyRequest().authenticated();
//        });
//        http.oauth2Login(oauth2 -> {
////            oauth2.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.authorizationRequestResolver(null));
//            oauth2.tokenEndpoint(tokenEndpoint -> {
////                DingTalkAuthorizationCodeTokenResponseClient tokenResponseClient = new DingTalkAuthorizationCodeTokenResponseClient();
//                DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
//                MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//                OAuth2AccessTokenResponseHttpMessageConverter messageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
//
//                messageConverter.setTokenResponseConverter(map -> {
//                    String accessToken = map.get("accessToken");
//                    String refreshToken = map.get("refreshToken");
//                    long expiresIn = Long.parseLong(map.get("expireIn"));
////                        String scopes = map.get("scopes");
//                    OAuth2AccessTokenResponse.Builder builder =
//                            OAuth2AccessTokenResponse.withToken(accessToken)
//                                    .tokenType(OAuth2AccessToken.TokenType.BEARER)
//                                    .expiresIn(expiresIn);
////                                .scopes(scopes)
//                    Map<String, Object> additionalParameters = new HashMap<>(1);
//                    additionalParameters.put("id_token", "");
//                    builder.additionalParameters(additionalParameters);
//                    builder.refreshToken(refreshToken);
//                    return builder.build();
//                });
//
//                RestTemplate restTemplate = new RestTemplate(
//                        Arrays.asList(new FormHttpMessageConverter(), messageConverter, jackson2HttpMessageConverter));
//                restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
//                tokenResponseClient.setRestOperations(restTemplate);
//
//                DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter requestEntityConverter = new DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter();
//                tokenResponseClient.setRequestEntityConverter(requestEntityConverter);
//
//                tokenEndpoint.accessTokenResponseClient(tokenResponseClient);
//            });
//            oauth2.userInfoEndpoint(userInfoEndpoint -> {
////                userInfoEndpoint.userService()
////                userInfoEndpoint.oidcUserService();
////                userInfoEndpoint.customUserType()
//            });
//        });
//        http.oauth2Client();
//        DingTalkAuthCodeConvertFilter dingTalkAuthCodeConvertFilter = new DingTalkAuthCodeConvertFilter();
//        http.addFilterBefore(dingTalkAuthCodeConvertFilter, OAuth2LoginAuthenticationFilter.class);
//        return http.build();
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    UserDetailsService users() {
//        UserDetails demo = User.builder()
//                .username("demo")
//                .password(passwordEncoder.encode("123456"))
//                .roles("DEMO")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("123456"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(demo, admin);
//    }

}
