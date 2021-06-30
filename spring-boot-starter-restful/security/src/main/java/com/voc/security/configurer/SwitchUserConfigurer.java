package com.voc.security.configurer;

import com.voc.security.core.authentication.restful.RestfulAuthenticationFailureHandler;
import com.voc.security.core.authentication.restful.RestfulAuthenticationSuccessHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/10 15:39
 */
public class SwitchUserConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<CustomLoginPageConfigurer<H>, H> {

    private SwitchUserFilter switchUserFilter = new SwitchUserFilter();

    private UserDetailsService userDetailsService;

    private AuthenticationSuccessHandler successHandler;

    private AuthenticationFailureHandler failureHandler;

    @Override
    public void init(H http) throws Exception {
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        userDetailsService = applicationContext.getBean(UserDetailsService.class);
        successHandler = applicationContext.getBean(RestfulAuthenticationSuccessHandler.class);
        failureHandler = applicationContext.getBean(RestfulAuthenticationFailureHandler.class);
    }

    @Override
    public void configure(H http) throws Exception {
        switchUserFilter.setUserDetailsService(userDetailsService);
        switchUserFilter.setSuccessHandler(successHandler);
        switchUserFilter.setFailureHandler(failureHandler);
        switchUserFilter = postProcess(switchUserFilter);
        http.addFilterAfter(switchUserFilter, FilterSecurityInterceptor.class);
    }

}
