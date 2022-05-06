package com.voc.restful.security.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.voc.restful.security.jose.Jwks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwsEncoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/27 20:15
 */
@Slf4j
@Configuration
public class AuthorizationServerConfiguration {

    @Resource
    private AuthenticationSuccessHandler restfulAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler restfulAuthenticationFailureHandler;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        /* 授权服务器配置 */
        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer<>();

        /* 授权端点 */
        authorizationServerConfigurer.authorizationEndpoint(config -> {
            config.errorResponseHandler(restfulAuthenticationFailureHandler);
        });

        /* token 端点 */
        authorizationServerConfigurer.tokenEndpoint(tokenEndpoint -> {
            tokenEndpoint.accessTokenResponseHandler(restfulAuthenticationSuccessHandler);
            tokenEndpoint.errorResponseHandler(restfulAuthenticationFailureHandler);
        });
        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();

        http
                .requestMatcher(endpointsMatcher)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .apply(authorizationServerConfigurer)
                .and().formLogin();

        return http.build();
    }


    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
//        RegisteredClient registeredClient = RegisteredClient.withId("1")
//                // 客户端id 需要唯一
//                .clientId("test")
//                .clientName("测试客户端")
//                // 客户端密码
//                .clientSecret("{noop}test123")
//                // 可以基于 basic 的方式和授权服务器进行认证
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_JWT)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.PRIVATE_KEY_JWT)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
//                // 授权码
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                // 如果需要支持双令牌，则需要添加此授权类型
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                // 客户端模式
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .authorizationGrantType(AuthorizationGrantType.JWT_BEARER)
//                // 授权码模式重定向url
//                .redirectUri("http://localhost:8090/callback")
//                .redirectUri("http://127.0.0.1:8090/callback")
//                .redirectUri("http://127.0.0.1:9000/login/oauth2/code/csdn")
//                // 客户端申请的作用域，也可以理解这个客户端申请访问用户的哪些信息，比如：获取用户信息，获取用户照片等
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .clientSettings(
//                        ClientSettings.builder()
//                                // 是否需要用户确认一下客户端需要获取用户的哪些权限
//                                // 比如：客户端需要获取用户的 用户信息、用户照片 但是此处用户可以控制只给客户端授权获取 用户信息。
//                                .requireAuthorizationConsent(true)
//                                .build()
//                )
//                .tokenSettings(
//                        TokenSettings.builder()
//                                // accessToken 的有效期
//                                .accessTokenTimeToLive(Duration.ofHours(2))
//                                .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
//                                // refreshToken 的有效期
//                                .refreshTokenTimeToLive(Duration.ofDays(7))
//                                // 是否可重用刷新令牌
//                                .reuseRefreshTokens(false)
//                                .build()
//                )
//                .build();

        RegisteredClient demoClient = RegisteredClient.withId("2")
                .clientId("demo")
                .clientName("演示客户端")
                .clientSecret("{noop}123456")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://127.0.0.1:9000/login/oauth2/code/dingtalk")
                .redirectUri("http://127.0.0.1:8090/callback")
                .scope(OidcScopes.OPENID)
                .clientSettings(
                        ClientSettings.builder()
                                // 是否需要用户确认一下客户端需要获取用户的哪些权限
                                // 比如：客户端需要获取用户的 用户信息、用户照片 但是此处用户可以控制只给客户端授权获取 用户信息。
                                .requireAuthorizationConsent(true)
                                .build()
                )
                .tokenSettings(
                        TokenSettings.builder()
                                // accessToken 的有效期
                                .accessTokenTimeToLive(Duration.ofHours(2))
                                // 访问令牌格式
                                .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                                // refreshToken 的有效期
                                .refreshTokenTimeToLive(Duration.ofDays(7))
                                // 是否可重用刷新令牌
                                .reuseRefreshTokens(false)
                                .build()
                )
                .build();

        JdbcRegisteredClientRepository jdbcRegisteredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
//        jdbcRegisteredClientRepository.save(registeredClient);
        jdbcRegisteredClientRepository.save(demoClient);

        return jdbcRegisteredClientRepository;
    }

    /**
     * 保存授权信息，授权服务器给我们颁发来token，那我们肯定需要保存吧，由这个服务来保存
     *
     * @param jdbcTemplate               JdbcTemplate
     * @param registeredClientRepository RegisteredClientRepository
     * @return OAuth2AuthorizationConsentService
     */
    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        JdbcOAuth2AuthorizationService authorizationService = new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
        class CustomOAuth2AuthorizationRowMapper extends JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper {
            public CustomOAuth2AuthorizationRowMapper(RegisteredClientRepository registeredClientRepository) {
                super(registeredClientRepository);
                getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                this.setLobHandler(new DefaultLobHandler());
            }
        }

        CustomOAuth2AuthorizationRowMapper oAuth2AuthorizationRowMapper =
                new CustomOAuth2AuthorizationRowMapper(registeredClientRepository);

//        authorizationService.setAuthorizationRowMapper(oAuth2AuthorizationRowMapper);

        return authorizationService;
    }


    /**
     * 如果是授权码的流程，可能客户端申请了多个权限，比如：获取用户信息，修改用户信息
     * 此Service处理的是用户给这个客户端哪些权限，比如只给获取用户信息的权限
     *
     * @param jdbcTemplate               JdbcTemplate
     * @param registeredClientRepository RegisteredClientRepository
     * @return OAuth2AuthorizationConsentService
     */
    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 授权服务器公私钥配置
     *
     * @return RSAKey
     */
    @Bean
    @ConditionalOnMissingBean
    public RSAKey rsaKey() {
        return Jwks.generateRsa("pub.key", "pri.key");
    }

    /**
     * 对JWT进行签名的 加解密密钥
     *
     * @param rsaKey RSAKey
     * @return JWKSource<SecurityContext>
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        JWKSet jwkSet = new JWKSet(Collections.singletonList(rsaKey));
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwsEncoder(jwkSource);
    }

    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder().build();
    }

}
