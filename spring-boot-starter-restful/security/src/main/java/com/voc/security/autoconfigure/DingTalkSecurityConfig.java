package com.voc.security.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/24 09:58
 */
//@AutoConfigureAfter(BeanConfig.class)
@Configuration
@ConditionalOnClass({})
public class DingTalkSecurityConfig {

//    @Bean
//    @ConditionalOnMissingBean
//    QRAuthenticationUserDetailsService dingTalkAuthenticationUserDetailsService(UserService<Serializable> userService) {
//        return new QRAuthenticationUserDetailsService(userService);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    PreAuthenticatedAuthenticationProvider dingTalkAuthenticationProvider(
//                   @Qualifier("dingTalkAuthenticationUserDetailsService") AuthenticationUserDetailsService authenticationUserDetailsService) {
//        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
////        authenticationProvider.
//        authenticationProvider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService);
//        return authenticationProvider;
//    }



//    @Bean
//    DaoAuthenticationProvider daoAuthenticationProvider() {
//        return new DaoAuthenticationProvider();
//    }

//    @Bean
//    DingTalkAuthenticationFilter dingTalkAuthenticationFilter(RestfulAuthenticationSuccessHandler successHandler,
//                                                              RestfulAuthenticationFailureHandler failureHandler) {
//        DingTalkAuthenticationFilter dingTalkAuthenticationFilter = new DingTalkAuthenticationFilter();
//        dingTalkAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
//        dingTalkAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
//
//        return dingTalkAuthenticationFilter;
//    }


}
