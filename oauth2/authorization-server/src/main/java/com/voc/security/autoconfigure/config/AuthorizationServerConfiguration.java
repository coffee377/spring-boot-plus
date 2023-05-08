package com.voc.security.autoconfigure.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.voc.security.core.authentication.ResultAuthenticationEntryPoint;
import com.voc.security.core.authentication.ResultAuthenticationFailureHandler;
import com.voc.security.core.authentication.ResultAuthenticationSuccessHandler;
import com.voc.security.jose.Jwks;
import com.voc.security.oauth2.servcer.OAuth2UsernamePasswordAuthenticationConverter;
import com.voc.security.oauth2.servcer.OAuth2UsernamePasswordAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextRepository;
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
    ResultAuthenticationEntryPoint resultAuthenticationEntryPoint;

    @Resource
    ResultAuthenticationFailureHandler resultAuthenticationFailureHandler;

    @Resource
    ResultAuthenticationSuccessHandler resultAuthenticationSuccessHandler;

    @Resource
    UserDetailsService userDetailsService;

    @Resource
    PasswordEncoder passwordEncoder;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        /* 授权服务器配置 */
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();
        http.apply(authorizationServerConfigurer);

        authorizationServerConfigurer
//                .registeredClientRepository(registeredClientRepository)
//                .authorizationService(authorizationService)
//                .authorizationConsentService(authorizationConsentService)
//                .authorizationServerSettings(authorizationServerSettings)
//                .tokenGenerator(tokenGenerator)
                .authorizationServerMetadataEndpoint(metadataEndpoint -> {
                    metadataEndpoint.authorizationServerMetadataCustomizer(builder -> {
                                // oauth2 授权类型支持密码模式
                                builder.grantType(AuthorizationGrantType.PASSWORD.getValue());

                            }
                    );
                })
                .clientAuthentication(clientAuthentication -> {
//                    clientAuthentication.
                })
                /* 授权端点 */
                .authorizationEndpoint(authorizationEndpoint -> {
//                    authorizationEndpoint.
//                    authorizationEndpoint.errorResponseHandler(resultAuthenticationFailureHandler);
                })
                /* token 端点 */
                .tokenEndpoint(tokenEndpoint -> {
                    tokenEndpoint.accessTokenRequestConverter(new OAuth2UsernamePasswordAuthenticationConverter());
                    OAuth2UsernamePasswordAuthenticationProvider passwordAuthenticationProvider = new OAuth2UsernamePasswordAuthenticationProvider(userDetailsService, passwordEncoder);
                    tokenEndpoint.authenticationProvider(passwordAuthenticationProvider);
//                    tokenEndpoint.accessTokenResponseHandler(resultAuthenticationSuccessHandler);
                    tokenEndpoint.errorResponseHandler(resultAuthenticationFailureHandler);
                })
                .tokenIntrospectionEndpoint(tokenIntrospectionEndpoint -> {
                })
                .tokenRevocationEndpoint(tokenRevocationEndpoint -> {
                })
                .oidc(oidc -> oidc
                        .userInfoEndpoint(userInfoEndpoint -> {
                        })
                        .clientRegistrationEndpoint(clientRegistrationEndpoint -> {
                        })
                );

        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        http.requestMatcher(endpointsMatcher)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()
                )
//                .httpBasic().and()
                .cors().and()
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .exceptionHandling(exceptions -> {
                    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login");
                    exceptions.authenticationEntryPoint(loginUrlAuthenticationEntryPoint);
                });

        SecurityContextHolderFilter securityContextHolderFilter = new SecurityContextHolderFilter(new JwtSecurityContextRepository());
        http.addFilter(securityContextHolderFilter);
        return http.build();
    }


    @Bean
    @ConditionalOnMissingBean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        RegisteredClient dingtalk = RegisteredClient.withId("1")
                .clientId("dingopfniakkw72klkjv")
                .clientName("DingTalk")
                .clientSecret("{noop}6Il0DuPZPPIr-OG03uMrnqDNu_o03tpIkK03ScpuEPP6NAw7J52D0LWPvTjRf4BR")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://127.0.0.1:9000/login/oauth2/code/dingtalk")
                .scope(OidcScopes.OPENID)
                .scope("corpid")
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

        RegisteredClient demoClient = RegisteredClient.withId("2")
                .clientId("spa")
                .clientName("前端 SPA 应用")
//                .clientSecret("{noop}123456")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
//                .redirectUri("http://127.0.0.1:9000/login/oauth2/code/dingtalk")
                .redirectUri("http://127.0.0.1:8090/callback")
                .redirectUri("http://127.0.0.1:3000/login")
                .redirectUri("http://127.0.0.1:5173/login")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope(OidcScopes.EMAIL)
                .scope(OidcScopes.PHONE)
                .scope("tweet.read")
                .scope("users.read")
                .scope("follows.read")
                .clientSettings(
                        ClientSettings.builder()
                                // 是否需要用户确认一下客户端需要获取用户的哪些权限
                                // 比如：客户端需要获取用户的 用户信息、用户照片 但是此处用户可以控制只给客户端授权获取 用户信息。
//                                .requireAuthorizationConsent(true)
                                // 是否启用 PKCE 全称是 Proof Key for Code Exchange（代码交换证明密钥）
//                                .requireProofKey(true)
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
        jdbcRegisteredClientRepository.save(dingtalk);
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
//        class CustomOAuth2AuthorizationRowMapper extends JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper {
//            public CustomOAuth2AuthorizationRowMapper(RegisteredClientRepository registeredClientRepository) {
//                super(registeredClientRepository);
//                getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//                this.setLobHandler(new DefaultLobHandler());
//            }
//        }
//
//        CustomOAuth2AuthorizationRowMapper oAuth2AuthorizationRowMapper =
//                new CustomOAuth2AuthorizationRowMapper(registeredClientRepository);
//
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

//    @Bean
//    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
//        return new NimbusJwtEncoder(jwkSource);
//    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

}
