package com.voc.security.autoconfigure;

import com.voc.api.utils.CommonUtil;
import com.voc.security.SecurityProperties;
import com.voc.security.authentication.RestfulLogoutSuccessHandler;
import com.voc.security.configurer.RestfulLoginConfigurer;
import com.voc.security.configurer.SwitchUserConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Security 配置
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/14 10:07
 */
@Order(101)
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties({SecurityProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SLASH = "/";

    public WebSecurityConfig() {
        super(false);
    }

//    @Resource
//    private ClientRegistrationRepository clientRegistrationRepository;

//    @Resource
//    private OAuth2AuthorizedClientService oauth2AuthorizedClientService;

//    @Resource
//    private UserDetailsService userDetailsService;

    @Resource
    private SecurityProperties securityProps;

    @Resource
    private AuthenticationEntryPoint restfulAuthenticationEntryPoint;

    @Resource
    private AccessDeniedHandler restfulAccessDeniedHandler;

    @Resource
    private AuthenticationSuccessHandler restfulAuthenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandler restfulAuthenticationFailureHandler;

    @Resource
    private RestfulLogoutSuccessHandler restfulLogoutSuccessHandler;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/favicon.ico", "/error");
        /* 自定义不需要验证权限的URL */
        this.customAntMatchers(web, securityProps.getIgnore());
    }

    private void customAntMatchers(WebSecurity web, List<String> antMatchers) {
        Optional.ofNullable(antMatchers).ifPresent(
                it -> it.forEach(
                        item -> {
                            if (!item.startsWith(SLASH)) {
                                item = SLASH + item;
                            }
                            web.ignoring().antMatchers(item);
                        }
                )
        );
    }

//    /**
//     * 覆盖默认的 TokenResolver
//     * token 支持从参数 access_token 获取
//     *
//     * @return BearerTokenResolver
//     */
//    @Bean
//    BearerTokenResolver bearerTokenResolver() {
//        DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
//        bearerTokenResolver.setAllowFormEncodedBodyParameter(true);
//        bearerTokenResolver.setAllowUriQueryParameter(true);
//        return bearerTokenResolver;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().headers().frameOptions().disable();

        /* org.springframework.security.web.context.SecurityContextPersistenceFilter */
        http.securityContext();

        /* 禁用 session */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /* 访问权限控制 */
        http.authorizeRequests(
                authorize -> {
                    String loginProcessUrl = securityProps.getLoginProcessUrl();
                    String logoutProcessUrl = securityProps.getLogoutProcessUrl();
                    if (CommonUtil.isCustomProcessUrl(loginProcessUrl)) {
                        authorize.antMatchers(loginProcessUrl).permitAll();
                    }
                    if (CommonUtil.isCustomProcessUrl(logoutProcessUrl)) {
                        authorize.antMatchers(logoutProcessUrl).permitAll();
                    }
                    authorize
                            .antMatchers(HttpMethod.OPTIONS).permitAll()
                            .antMatchers("/token").permitAll()
                            .antMatchers("/error", "/login/**", "/oauth2/**", "/callback").permitAll()
                            .anyRequest().authenticated();
                }
        );

        /* 异常处理 */
        http.exceptionHandling(handling -> {
            handling.authenticationEntryPoint(restfulAuthenticationEntryPoint);
            handling.accessDeniedHandler(restfulAccessDeniedHandler);
        });

        /* 表单登陆处理 */
//        禁用默认表单登陆
        http.formLogin(form -> {
            form.successHandler(restfulAuthenticationSuccessHandler);
            form.disable();
        });

        /* 注销处理 */
        http.logout(httpSecurityLogoutConfigurer -> {
            /*httpSecurityLogoutConfigurer.logoutUrl("/logout");*/
            httpSecurityLogoutConfigurer.logoutSuccessHandler(restfulLogoutSuccessHandler);
            String logoutProcessUrl = securityProps.getLogoutProcessUrl();
            if (CommonUtil.isCustomProcessUrl(logoutProcessUrl)) {
                httpSecurityLogoutConfigurer.logoutUrl(logoutProcessUrl);
            }
        });

        /* http.httpBasic();*/

        /* 用户名密码 Restful 登陆处理 */
        http.apply(new RestfulLoginConfigurer<>());

        /* 启用切换用户过滤器 */
        http.apply(new SwitchUserConfigurer<>());
//
//        /* oauth2 登录 */
//        http.oauth2Login(oauth2 -> {
////            oauth2.loginPage()
//            oauth2.authorizationEndpoint(authorization -> {
////                authorization.authorizationRequestRepository(new AuthorizationRequestRepository<OAuth2AuthorizationRequest>() {
////                    @Override
////                    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
////                        return null;
////                    }
////
////                    @Override
////                    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
////
////                    }
////
////                    @Override
////                    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
////                        return null;
////                    }
////                });
////                authorization.
////                authorization.authorizationRequestResolver(new OAuth2AuthorizationRequestResolver() {
////                    @Override
////                    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
////                        return null;
////                    }
////
////                    @Override
////                    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
////                        return null;
////                    }
////                });
////                authorization.baseUri("/oauth2/authorization/{id}");
//            }).redirectionEndpoint(redirection -> {
////                redirection.baseUri("/login/oauth2/code/*");
//            }).tokenEndpoint(token -> {
////                token.accessTokenResponseClient(new DefaultAuthorizationCodeTokenResponseClient());
////                token.accessTokenResponseClient()
//            }).userInfoEndpoint(userInfo -> {
////                userInfo.customUserType()
////                userInfo.userAuthoritiesMapper(new GrantedAuthoritiesMapper() {
////                    @Override
////                    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
////                        return null;
////                    }
////                });
////                userInfo.
////                userInfo.userService()
//            });
//            oauth2.successHandler(restfulAuthenticationSuccessHandler);
//            oauth2.failureHandler(restfulAuthenticationFailureHandler);
////                .loginPage("/login/oauth2")
//        });

//        /* oauth2 客户端 */
//        http.oauth2Client(client -> {
////            client.authorizationCodeGrant().;
////            client.authorizationCodeGrant().
//        });
//
//        /* oauth2 资源认证服务 */
//        http.oauth2ResourceServer(resourceServer -> {
//            resourceServer.authenticationEntryPoint(restfulAuthenticationEntryPoint);
//            resourceServer.accessDeniedHandler(restfulAccessDeniedHandler);
//            resourceServer.jwt(jwt -> {
////                jwt.
////                jwt.decoder(token -> {
////                    Jwt.Builder builder = Jwt.withTokenValue(token);
////                    return builder.build();
////                });
//            });
////            resourceServer.opaqueToken(opaque -> {
////
////            });
//        });

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }

}
