package com.voc.api.security;

import com.voc.api.security.authentication.RestfulAuthenticationFilter;
import com.voc.api.security.authentication.RestfulLogoutSuccessHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Security 配置
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/14 10:07
 */
@Order(2)
@EnableWebSecurity
@EnableConfigurationProperties({SecurityProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SLASH = "/";

    @Resource
    private ClientRegistrationRepository clientRegistrationRepository;

    @Resource
    private OAuth2AuthorizedClientService oauth2AuthorizedClientService;

    @Resource
    private UserDetailsService userDetailsService;

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

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 注册自定义的 AuthenticationFilter
     */
    @Bean
    public RestfulAuthenticationFilter restfulAuthenticationFilter() throws Exception {
        RestfulAuthenticationFilter filter = new RestfulAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(restfulAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(restfulAuthenticationFailureHandler);
        /* 登录处理地址 */
        filter.setFilterProcessesUrl("/login/password");
        /*重用 WebSecurityConfigurerAdapter 配置的 AuthenticationManager，不然要自己组装 AuthenticationManager*/
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

//    @Bean
//    public OAuth2LoginAuthenticationFilter auth2LoginAuthenticationFilter() throws Exception {
//        OAuth2LoginAuthenticationFilter filter =
//                new OAuth2LoginAuthenticationFilter(clientRegistrationRepository, oauth2AuthorizedClientService);
//        filter.setAuthenticationSuccessHandler(restfulAuthenticationSuccessHandler);
//        filter.setAuthenticationFailureHandler(restfulAuthenticationFailureHandler);
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().headers().frameOptions().disable();
//        http.formLogin().disable();

        http.authorizeRequests(
                authorize -> authorize
                        .antMatchers(HttpMethod.OPTIONS).permitAll()
                        .antMatchers("/home", "/login/**", "/logout", "/api/test", "/oauth2/**", "/callback").permitAll()
                        .mvcMatchers("/messages/**").hasAuthority("SCOPE_messages")
                        .anyRequest().authenticated()
        );

        /* 异常处理 */
        http.exceptionHandling(handling -> {
            handling.authenticationEntryPoint(restfulAuthenticationEntryPoint);
            handling.accessDeniedHandler(restfulAccessDeniedHandler);
        });

        /* 注销处理 */
        http.logout(httpSecurityLogoutConfigurer -> {
            httpSecurityLogoutConfigurer.logoutUrl("/logout");
            httpSecurityLogoutConfigurer.logoutSuccessHandler(restfulLogoutSuccessHandler);
        });
//
//        /* 禁用 session */
////        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
//
        /* 用重写的 Filter 替换掉原有的UsernamePasswordAuthenticationFilter */
        http.addFilterAt(restfulAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

//        /* 启用切换用户过滤器 */
////        http.addFilterAfter(switchUserFilter(), FilterSecurityInterceptor.class);
//
////        http.oauth2Login(new Customsizer<OAuth2LoginConfigurer<HttpSecurity>>() {
////            @Override
////            public void customize(OAuth2LoginConfigurer<HttpSecurity> httpSecurityOAuth2LoginConfigurer) {
////
////            }
////        });
//
//        /* oauth 资源认证服务 */
////        http.oauth2ResourceServer().jwt();
//        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
//
////        http.oauth2Client(OAuth2ClientConfigurer::authorizationCodeGrant);
        http.oauth2Login(oauth2 -> oauth2
                .successHandler(restfulAuthenticationSuccessHandler)
                .failureHandler(restfulAuthenticationFailureHandler)
                .loginPage("/login/oauth2")
        );
        http.oauth2Client();
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234567").roles("admin");
    }

    //token登陆处理
//    @Bean
//    public TokenAuthenticationProvider tokenAuthenticationProsvider() {
//        return new TokenAuthenticationProvider();
//    }
//    /**
//     * 添加token登陆验证的过滤器
//     */
//    @Bean
//    public TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
//        TokenAuthenticationFilter filter = new TokenAuthenticationFilter();
//        filter.setAuthenticationManager(authenticationManager());
//        return filter;
//    }
}
