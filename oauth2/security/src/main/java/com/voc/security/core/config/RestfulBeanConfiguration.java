package com.voc.security.core.config;

import com.voc.security.core.authentication.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/04 12:59
 */
@Configuration
public class RestfulBeanConfiguration {

    /**
     * 认证异常处理器
     *
     * @return AuthenticationEntryPoint
     */
    @Bean("restfulAuthenticationEntryPoint")
    @ConditionalOnMissingBean(RestfulAuthenticationEntryPoint.class)
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new RestfulAuthenticationEntryPoint();
    }

    /**
     * 无访问权限处理器
     *
     * @return AccessDeniedHandler
     */
    @Bean("restfulAccessDeniedHandler")
    @ConditionalOnMissingBean(RestfulAccessDeniedHandler.class)
    AccessDeniedHandler accessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    /**
     * 认证成功处理器
     *
     * @return AuthenticationSuccessHandler
     */
    @Bean("restfulAuthenticationSuccessHandler")
    @ConditionalOnMissingBean(RestfulAuthenticationSuccessHandler.class)
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RestfulAuthenticationSuccessHandler();
    }

    /**
     * 认证失败处理器
     *
     * @return AuthenticationFailureHandler
     */
    @Bean("restfulAuthenticationFailureHandler")
    @ConditionalOnMissingBean(RestfulAuthenticationFailureHandler.class)
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new RestfulAuthenticationFailureHandler();
    }

    /**
     * 注销成功处理器
     *
     * @return LogoutSuccessHandler
     */
    @Bean("restfulLogoutSuccessHandler")
    @ConditionalOnMissingBean(RestfulLogoutSuccessHandler.class)
    LogoutSuccessHandler logoutSuccessHandler() {
        return new RestfulLogoutSuccessHandler();
    }

}
