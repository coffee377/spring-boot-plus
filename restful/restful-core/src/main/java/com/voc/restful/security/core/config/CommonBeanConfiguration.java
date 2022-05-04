package com.voc.restful.security.core.config;

import com.voc.restful.core.service.AuthService;
import com.voc.restful.core.service.impl.DefaultAuthService;
import com.voc.restful.security.core.authentication.DefaultUserDetailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/04 13:04
 */
@Configuration
public class CommonBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    AuthService authService() {
        return new DefaultAuthService();
    }

    @Bean
    @ConditionalOnMissingBean
    UserDetailsService userDetailsService(AuthService<Serializable> authService) {
        return new DefaultUserDetailService(authService);
    }

//    @Bean
//    @ConditionalOnMissingBean
//    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//        /* // TODO: 2022/3/28 21:18 根据配置是否隐藏用户不存在异常 */
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        return daoAuthenticationProvider;
//    }
}
