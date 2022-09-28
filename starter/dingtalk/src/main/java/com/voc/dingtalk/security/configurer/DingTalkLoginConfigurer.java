//package com.voc.dingtalk.security.configurer;
//
//import com.voc.dingtalk.security.authentication.DingTalkAuthenticationFilter;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
//import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/06/25 15:24
// */
//public class DingTalkLoginConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, DingTalkLoginConfigurer<H>, DingTalkAuthenticationFilter> {
//
//    public DingTalkLoginConfigurer() {
//        super(new DingTalkAuthenticationFilter(), DingTalkAuthenticationFilter.DEFAULT_LOGIN_PROCESS_URL);
//    }
//
//    @Override
//    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
//        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
//    }
//
//    @Override
//    public void configure(H http) throws Exception {
//        DingTalkAuthenticationFilter authFilter = getAuthenticationFilter();
//
//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//        authFilter.setAuthenticationManager(authenticationManager);
//
//        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
//
//        AuthenticationSuccessHandler authenticationSuccessHandler = (AuthenticationSuccessHandler) applicationContext.getBean("restfulAuthenticationSuccessHandler");
//        AuthenticationFailureHandler authenticationFailureHandler = (AuthenticationFailureHandler) applicationContext.getBean("restfulAuthenticationFailureHandler");
//        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//
//        authFilter = postProcess(authFilter);
//
//        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
//
//    }
//}
