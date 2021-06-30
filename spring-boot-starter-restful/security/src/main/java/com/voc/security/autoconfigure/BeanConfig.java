package com.voc.security.autoconfigure;

import com.voc.restful.core.service.UserService;
import com.voc.restful.core.service.impl.DefaultUserService;
import com.voc.security.core.authentication.*;
import com.voc.security.core.authentication.restful.*;
import com.voc.security.oauth2.OAuth2Properties;
import com.voc.security.oauth2.client.web.DelegateOAuth2AuthorizationRequestResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DelegatingOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/11 16:51
 */
@Slf4j
@EnableConfigurationProperties(OAuth2Properties.class)
public class BeanConfig {

    @Bean
    @ConditionalOnMissingBean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean(UserService.BEAN_NAME)
    @ConditionalOnClass(SecurityAutoConfiguration.class)
    @ConditionalOnMissingBean
    UserService userService() {
        return new DefaultUserService();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    UserDetailsService userDetailsService(UserService<Serializable> userService) {
        return new DefaultUserDetailService(userService);
    }

    /**
     * 认证异常处理器
     *
     * @return AuthenticationEntryPoint
     */
    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    AuthenticationEntryPoint restfulAuthenticationEntryPoint() {
        return new RestfulAuthenticationEntryPoint();
    }

    /**
     * 没有访问权限处理器
     *
     * @return AccessDeniedHandler
     */
    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    AccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    /**
     * 认证成功处理器
     *
     * @param eventPublisher 事件发布
     * @return AuthenticationSuccessHandler
     */
    @Bean
    @ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
    AuthenticationSuccessHandler restfulAuthenticationSuccessHandler(ApplicationEventPublisher eventPublisher) {
        return new RestfulAuthenticationSuccessHandler();
    }

    /**
     * 认证失败处理器
     *
     * @return AuthenticationFailureHandler
     */
    @Bean
    @ConditionalOnMissingBean(AuthenticationFailureHandler.class)
    AuthenticationFailureHandler restfulAuthenticationFailureHandler() {
        return new RestfulAuthenticationFailureHandler();
    }

    /**
     * 注销成功处理器
     *
     * @return LogoutSuccessHandler
     */
    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    LogoutSuccessHandler restfulLogoutSuccessHandler() {
        return new RestfulLogoutSuccessHandler();
    }

    //    @Bean
//    @ConditionalOnMissingBean(JwtDecoder.class)
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("").build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("test")
                .clientSecret("{noop}test")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1")
                .scope(OidcScopes.OPENID)
                .scope("message.read")
                .scope("message.write")
                .clientSettings(clientSettings -> clientSettings.requireUserConsent(true))
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    @Bean
    public ProviderSettings providerSettings() {
        return new ProviderSettings().issuer("http://auth-server:8080");
    }


    /**
     * 增强后的 OAuth2AuthorizationRequestResolver 处理器，可修改参数
     *
     * @param clientRegistrationRepository ClientRegistrationRepository
     * @return OAuth2AuthorizationRequestResolver
     */
    @Bean
    @ConditionalOnMissingBean
    OAuth2AuthorizationRequestResolver authorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository, OAuth2Properties properties) {
        String authorizationRequestBaseUri = properties.getAuthorizationRequestBaseUri();
        return new DelegateOAuth2AuthorizationRequestResolver(clientRegistrationRepository, authorizationRequestBaseUri);
    }

    @Bean("delegatingOAuth2UserService")
    @ConditionalOnMissingBean
    DelegatingOAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(List<OAuth2UserService<OAuth2UserRequest, OAuth2User>> userServices) {
        return new DelegatingOAuth2UserService<>(userServices);
    }

}
