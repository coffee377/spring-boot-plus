package com.voc.api.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Security 配置
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/14 10:07
 */
@Configuration
@Order(1)
@EnableWebSecurity
@EnableConfigurationProperties({SecurityProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SLASH = "/";

    @Resource
    private SecurityProperties securityProps;

    @Resource
    private AuthenticationEntryPoint restfulAuthenticationEntryPoint;

    @Resource
    private AccessDeniedHandler restfulAccessDeniedHandler;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/", "/error", "/*.ico", "/assets/**", "/webjars/**");
        web.ignoring().antMatchers("/layui/**", "/map/**", "/zui/**", "/swagger-ui/**");
        /* 自定义不需要验证权限的URL */
        this.customAntMatchers(web, securityProps.getIgnore());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable();
//        http.formLogin().permitAll();
        http.formLogin().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/actuator/**", "/monitor/**", "/login/**").permitAll()
                .antMatchers("/api/test").permitAll()
                .anyRequest().authenticated();

        /* 异常处理 */
//        http.exceptionHandling(handling -> {
//            handling.authenticationEntryPoint(restfulAuthenticationEntryPoint);
//            handling.accessDeniedHandler(restfulAccessDeniedHandler);
//        });
        http.exceptionHandling()
                .authenticationEntryPoint(restfulAuthenticationEntryPoint)
                .accessDeniedHandler(restfulAccessDeniedHandler);
//        http.authorizeRequests().anyRequest().authenticated();
//        super.configure(http);
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .antMatchers("/oauth/**").authenticated()
//                .and().formLogin().loginPage("/login").permitAll().failureUrl("/login?error");
//                .failureHandler(new DefaultAuthenticationFailureHandler()).and().rememberMe()
//                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .addLogoutHandler(logoutAndRemoveTokenHandler).permitAll()
//                .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).accessDeniedPage("/access?error");
//        http.logout(new Customizer<LogoutConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer) {
//                httpSecurityLogoutConfigurer.logoutUrl("/logout");
//                httpSecurityLogoutConfigurer.logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//                    }
//                });
//            }
//        });
    }

    @Bean
    public UserDetailsService userDetailsServiceImpl() {
        return new UserDetailServiceImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(passwordEncoder());
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
}
