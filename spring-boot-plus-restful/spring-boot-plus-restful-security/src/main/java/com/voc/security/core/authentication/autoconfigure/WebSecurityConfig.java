//package com.voc.security.core.authentication.autoconfigure;
//
//import com.voc.api.utils.CommonUtil;
//import com.voc.security.SecurityProperties;
//import com.voc.security.core.authentication.restful.RestfulLogoutSuccessHandler;
//import com.voc.security.oauth2.OAuth2Properties;
//import com.voc.security.oauth2.client.endpoint.DelegateOAuth2AccessTokenResponseClient;
//import com.voc.security.oauth2.client.web.InMemoryOAuth2AuthorizationRequestRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.client.userinfo.DelegatingOAuth2UserService;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.annotation.Resource;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * Security 配置
// *
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2018/03/14 10:07
// */
//@Order(1)
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    public static final String SLASH = "/";
//
//    public WebSecurityConfig() {
//        super(false);
//    }
//
//    @Resource
//    private SecurityProperties securityProps;
//
//    @Resource
//    private AuthenticationEntryPoint restfulAuthenticationEntryPoint;
//
//    @Resource
//    private AccessDeniedHandler restfulAccessDeniedHandler;
//
//    @Resource
//    private AuthenticationSuccessHandler restfulAuthenticationSuccessHandler;
//
//    @Resource
//    private AuthenticationFailureHandler restfulAuthenticationFailureHandler;
//
//    @Resource
//    private RestfulLogoutSuccessHandler restfulLogoutSuccessHandler;
//
//    @Resource
//    private BearerTokenResolver bearerTokenResolver;
//
//    @Resource
//    private JwtDecoder jwtDecoder;
//
//    @Resource
//    private OAuth2AuthorizationRequestResolver oauth2AuthorizationRequestResolver;
//
//    @Resource
//    private OAuth2Properties oauth2Properties;
//
//    @Resource
//    private DelegatingOAuth2UserService delegatingOAuth2UserService;
//
//    @Resource
//    private AuthenticationProvider authenticationProvider;
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        /* 自定义不需要验证权限的URL */
//        String[] ignoreUrls = this.ignoreUrls(securityProps.getIgnore(), "/favicon.ico", "/error");
//        web.ignoring().antMatchers(ignoreUrls);
//    }
//
//    private String[] ignoreUrls(List<String> ignore, String... extraUrl) {
//        List<String> list = Optional.ofNullable(ignore).orElse(new LinkedList<>());
//        Set<String> set = new HashSet<>(list);
//        set.addAll(Arrays.asList(extraUrl));
//        return set.stream().map(url -> {
//            if (!url.startsWith(SLASH)) {
//                return SLASH + url;
//            }
//            return url;
//        }).collect(Collectors.toList()).toArray(new String[]{});
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable().headers().frameOptions().disable();
//
//        /* org.springframework.security.web.context.SecurityContextPersistenceFilter */
////        http.securityContext().securityContextRepository(new TokenSecurityContextRepository());
//
////        BearerTokenAuthenticationFilter tokenAuthenticationFilter = new BearerTokenAuthenticationFilter(authenticationManagerBean());
////        tokenAuthenticationFilter.setBearerTokenResolver(bearerTokenResolver);
////        tokenAuthenticationFilter.setAuthenticationEntryPoint(restfulAuthenticationEntryPoint);
////        tokenAuthenticationFilter.setAuthenticationFailureHandler(restfulAuthenticationFailureHandler);
////        http.addFilterBefore(tokenAuthenticationFilter, LogoutFilter.class);
//
//        /* 禁用 session */
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        /* 访问权限控制 */
//        http.authorizeRequests(
//                authorize -> {
//                    String loginProcessUrl = securityProps.getLoginProcessUrl();
//                    String logoutProcessUrl = securityProps.getLogoutProcessUrl();
//                    if (CommonUtil.isCustomProcessUrl(loginProcessUrl)) {
//                        authorize.antMatchers(loginProcessUrl).permitAll();
//                    }
//                    if (CommonUtil.isCustomProcessUrl(logoutProcessUrl)) {
//                        authorize.antMatchers(logoutProcessUrl).permitAll();
//                    }
//                    authorize
//                            .antMatchers(HttpMethod.OPTIONS).permitAll()
//                            .antMatchers("/oauth2/**","/logout").authenticated()
//                            .antMatchers("/.well-known/oauth-authorization-server").permitAll()
//                            .antMatchers("/error").permitAll()
//                            .anyRequest().authenticated();
//                }
//        );
//
//        /* 异常处理 */
//        http.exceptionHandling(handling -> {
//            handling.authenticationEntryPoint(restfulAuthenticationEntryPoint);
//            handling.accessDeniedHandler(restfulAccessDeniedHandler);
//        });
//
//        /* 表单登陆处理 */
////        禁用默认表单登陆
//        http.formLogin(form -> {
//            form.successHandler(restfulAuthenticationSuccessHandler);
//            form.disable();
//        });
//
////        http.addFilterAt()
//        /* 注销处理 */
//        http.logout(logoutConfigurer -> {
//            /*httpSecurityLogoutConfigurer.logoutUrl("/logout");*/
//            logoutConfigurer.permitAll();
////            logoutConfigurer.
////            logoutConfigurer.permitAll(false);
////            logoutConfigurer.addLogoutHandler(restfulLogoutHandler);
////            logoutConfigurer.clearAuthentication(false);
////            logoutConfigurer.logoutSuccessHandler(restfulLogoutSuccessHandler);
////            String logoutProcessUrl = securityProps.getLogoutProcessUrl();
////            if (CommonUtil.isCustomProcessUrl(logoutProcessUrl)) {
////                logoutConfigurer.logoutUrl(logoutProcessUrl);
////            }
//        });
//
//        /* http.httpBasic();*/
//
////        /* 用户名密码 Restful 登陆处理 */
////        http.apply(new RestfulLoginConfigurer<>());
//////        http.apply(new DingTalkLoginConfigurer<>());
////
////        /* 启用切换用户过滤器 */
////        http.apply(new SwitchUserConfigurer<>());
//
//        /* oauth2 登录 */
////        http.oauth2Login(oauth2 -> {
////            oauth2.successHandler(restfulAuthenticationSuccessHandler);
////            oauth2.failureHandler(restfulAuthenticationFailureHandler);
////            oauth2.authorizationEndpoint(authorizationEndpoint -> {
////                authorizationEndpoint.baseUri(oauth2Properties.getAuthorizationRequestBaseUri());
////                // TODO: 2021/6/22 11:48 这里需要改成内存实现或 redis 实现，不然 restful oauth2 认证流程走不下去
////                authorizationEndpoint.authorizationRequestRepository(new InMemoryOAuth2AuthorizationRequestRepository());
////                authorizationEndpoint.authorizationRequestResolver(oauth2AuthorizationRequestResolver);
////            });
////            oauth2.tokenEndpoint(tokenEndpoint -> {
////                tokenEndpoint.accessTokenResponseClient(new DelegateOAuth2AccessTokenResponseClient());
////            });
////            oauth2.redirectionEndpoint(redirectionEndpoint -> {
////                redirectionEndpoint.baseUri(oauth2Properties.getAuthorizationResponseBaseUri());
////            });
////            oauth2.userInfoEndpoint(userInfoEndpoint -> {
////                userInfoEndpoint.userService(delegatingOAuth2UserService);
////            });
////        });
//
//        /* oauth2 客户端 */
////        http.oauth2Client(client -> {
//////            client.
//////            client.clientRegistrationRepository()
////        });
//
//        /* oauth2 认证服务 */
////        OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();
////        http.apply(authorizationServerConfigurer);
//
//        /* oauth2 资源服务 */
//        http.oauth2ResourceServer(resourceServer -> {
//            resourceServer.bearerTokenResolver(bearerTokenResolver);
//            resourceServer.authenticationEntryPoint(restfulAuthenticationEntryPoint);
//            resourceServer.accessDeniedHandler(restfulAccessDeniedHandler);
//            resourceServer.jwt(jwt -> {
//                jwt.decoder(jwtDecoder);
//            });
//            resourceServer.opaqueToken();
//        });
//
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
////        super.configure(auth);
//    }
//}
