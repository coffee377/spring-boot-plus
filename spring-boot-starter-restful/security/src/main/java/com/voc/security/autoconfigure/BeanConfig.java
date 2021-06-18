package com.voc.security.autoconfigure;

import com.voc.security.authentication.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/11 16:51
 */
public class BeanConfig {

//    @Bean
//    @ConditionalOnMissingBean
//    AuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        return daoAuthenticationProvider;
//    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    UserDetailsService userDetailsService() {
//        return new DefaultUserDetailService();
        // TODO: 2021/6/18 15:41 实现具体的服务
        UserDetails admin = User.withUsername("admin").password("{noop}123456").authorities("ROLE_ADMIN").build();
        UserDetails test1 = User.withUsername("test").password("{noop}123456").authorities("SCOPE_test").build();
        UserDetails test2 = User.withUsername("demo").password("{noop}123456").authorities("SCOPE_demo").build();
        return new InMemoryUserDetailsManager(admin, test1, test2);
    }

    /**
     * 认证异常处理器
     *
     * @return AuthenticationEntryPoint
     */
    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    AuthenticationEntryPoint restfulAuthenticationEntryPoint() {
        return new RestfulAuthenticationEntryPoint("/login");
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


}
