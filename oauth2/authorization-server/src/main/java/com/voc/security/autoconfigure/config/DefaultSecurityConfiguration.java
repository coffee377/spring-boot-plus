package com.voc.security.autoconfigure.config;

import com.voc.security.core.authentication.ResultAuthenticationFailureHandler;
import com.voc.security.core.authentication.ResultAuthenticationSuccessHandler;
import com.voc.security.core.authentication.converter.OAuth2AccessTokenResponseConverter;
import com.voc.security.oauth2.client.dingtalk.DingTalkAuthCodeConvertFilter;
import com.voc.security.oauth2.client.dingtalk.DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter;
import com.voc.security.oauth2.client.provider.OAuth2ClientRegistrationAdapter;
import com.voc.security.oauth2.client.OAuth2UserRequestEntityConverterPlus;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/27 19:22
 */
@Configuration
@AutoConfigureBefore(OAuth2ClientAutoConfiguration.class)
public class DefaultSecurityConfiguration {

    /**
     * 授权请求自定义解析逻辑
     *
     * @param clientRegistrationRepository {@link ClientRegistrationRepository}
     * @return {@link DefaultOAuth2AuthorizationRequestResolver}
     */
    @Bean
    DefaultOAuth2AuthorizationRequestResolver authorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository) {
        DefaultOAuth2AuthorizationRequestResolver authorizationRequestResolver =
                new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI);
        authorizationRequestResolver.setAuthorizationRequestCustomizer(builder -> {
            builder.attributes(attributes -> {
                String registrationId = (String) attributes.get("registration_id");
                if ("dingtalk".equalsIgnoreCase(registrationId)) {
                    builder.parameters(parameters -> {
                        /* 授权确认 */
                        parameters.put("prompt", "consent");
                    });
                }
            });
        });
        return authorizationRequestResolver;
    }

    @Bean
    OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient authorizationCodeTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        authorizationCodeTokenResponseClient.setRequestEntityConverter(new DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter());

        OAuth2AccessTokenResponseHttpMessageConverter responseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
        responseHttpMessageConverter.setAccessTokenResponseConverter(new OAuth2AccessTokenResponseConverter());
        RestTemplate restTemplate = new RestTemplate(Arrays.asList(
                new FormHttpMessageConverter(),
                responseHttpMessageConverter,
                new MappingJackson2HttpMessageConverter()
        ));
        authorizationCodeTokenResponseClient.setRestOperations(restTemplate);

        return authorizationCodeTokenResponseClient;
    }

    @Bean
    @Order(10)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                          OAuth2AuthorizationRequestResolver authorizationRequestResolver,
                                                          OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient,
                                                          ResultAuthenticationFailureHandler resultAuthenticationFailureHandler,
                                                          ResultAuthenticationSuccessHandler resultAuthenticationSuccessHandler
    ) throws Exception {
        http
                .authorizeRequests((authorize) -> {
                    authorize
//                            .antMatchers("/account/**").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin().and()
                .httpBasic().and()
                .oauth2Login(loginConfigurer -> {
                    loginConfigurer.successHandler(resultAuthenticationSuccessHandler);
                    loginConfigurer.failureHandler(resultAuthenticationFailureHandler);
//                    loginConfigurer.clientRegistrationRepository()
                    loginConfigurer.authorizationEndpoint(authorizationEndpoint -> {
//                        authorizationEndpoint.baseUri()
                        authorizationEndpoint.authorizationRequestResolver(authorizationRequestResolver);
                    });

                    /* 令牌端点配置 */
                    loginConfigurer.tokenEndpoint(tokenEndpoint -> {
                        tokenEndpoint.accessTokenResponseClient(authorizationCodeTokenResponseClient);
                    });
                    /* 用户信息端点配置 */
                    loginConfigurer.userInfoEndpoint(userInfoEndpoint -> {
//                        userInfoEndpoint.customUserType(DingTalkOAuth2User.class, "dingtalk");
                        DefaultOAuth2UserService userService = new DefaultOAuth2UserService();
                        /* 请求参数转换 */
                        userService.setRequestEntityConverter(new OAuth2UserRequestEntityConverterPlus());
                        userInfoEndpoint.userService(userService);
                    });
                })
                .oauth2Client(clientConfigurer -> {
                    clientConfigurer.authorizationCodeGrant(authorizationCodeGrantConfigurer -> {
//                        authorizationCodeGrantConfigurer.
                    });
                })
                .csrf().disable();

        DingTalkAuthCodeConvertFilter dingTalkAuthCodeConvertFilter = new DingTalkAuthCodeConvertFilter();
        http.addFilterBefore(dingTalkAuthCodeConvertFilter, OAuth2LoginAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    ClientRegistrationRepository inMemoryClientRegistrationRepository(OAuth2ClientProperties properties) {
        List<ClientRegistration> registrations = new ArrayList<>(OAuth2ClientRegistrationAdapter.getClientRegistrations(properties).values());
        return new InMemoryClientRegistrationRepository(registrations);
    }

//    @Bean
//    OAuth2AuthorizedClientRepository authorizedClientRepository(){
//        return null;
//    }
}
