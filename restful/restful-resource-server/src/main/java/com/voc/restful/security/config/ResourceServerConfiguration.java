package com.voc.restful.security.config;

import com.voc.restful.security.authentication.RestfulAuthenticationEntryPoint;
import com.voc.restful.security.web.configurers.BearerTokenAuthenticationFilterConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/28 20:00
 */
@EnableWebSecurity
public class ResourceServerConfiguration {

    @Resource
    private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

    @Resource
    private AccessDeniedHandler restfulAccessDeniedHandler;

    @Resource
    private BearerTokenResolver bearerTokenResolver;

    @Resource
    private AuthenticationFailureHandler restfulAuthenticationFailureHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* 1. 禁用 session */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /* 2. 异常处理 */
        http.exceptionHandling(handling -> {
            handling.authenticationEntryPoint(restfulAuthenticationEntryPoint);
            handling.accessDeniedHandler(restfulAccessDeniedHandler);
        });

        http.apply(new BearerTokenAuthenticationFilterConfigurer<>());

        /* oauth2 资源服务配置 */
        http.oauth2ResourceServer(configurer -> {
            configurer.authenticationEntryPoint(restfulAuthenticationEntryPoint);
            configurer.accessDeniedHandler(restfulAccessDeniedHandler);
            configurer.jwt();
//            configurer.opaqueToken();
        });

        /* 权限配置 */
        http.authorizeRequests(
                authorize -> {
                    authorize
                            .antMatchers(HttpMethod.OPTIONS).permitAll()
                            .antMatchers("/messages/**").access("hasAuthority('SCOPE_message.read')")
                            .antMatchers("/menus/**").access("hasAuthority('SCOPE_menu.read')")
                            .anyRequest().authenticated();
                }
        );

        return http.build();
    }

}
