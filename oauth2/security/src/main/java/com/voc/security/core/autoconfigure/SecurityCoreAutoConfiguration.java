package com.voc.security.core.autoconfigure;

import com.voc.security.core.authentication.DefaultUserDetailService;
import com.voc.security.core.props.RootAccountProperties;
import com.voc.security.core.props.SecurityProperties;
import com.voc.security.core.autoconfigure.config.RestfulAuthenticationConfiguration;
import com.voc.security.core.service.AuthService;
import com.voc.security.core.service.impl.RootAccountService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/04 13:11
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class, RootAccountProperties.class})
@ConditionalOnClass(DefaultAuthenticationEventPublisher.class)
@AutoConfigureAfter(SecurityAutoConfiguration.class)
@Import({RestfulAuthenticationConfiguration.class, SecurityImport.class,})
public class SecurityCoreAutoConfiguration {

    /**
     * 注入默认密码加密器
     *
     * @return PasswordEncoder
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 注入系统根用户
     *
     * @param rootAccount 根用户配置
     * @return AuthService
     */
    @Bean
    AuthService rootAccountService(RootAccountProperties rootAccount) {
        return new RootAccountService(rootAccount);
    }

    /**
     * Spring Security 默认用户服务
     *
     * @param authServices 认证接口
     * @return UserDetailsService
     */
    @Bean
    @ConditionalOnMissingBean
    UserDetailsService userDetailsService(ObjectProvider<AuthService> authServices) {
        return new DefaultUserDetailService(authServices);
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
