package com.voc.api.security;

import com.voc.api.security.authentication.RestfulAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/02 11:50
 */
public class RestfulLoginConfigurer<H extends HttpSecurityBuilder<H>>
        extends AbstractAuthenticationFilterConfigurer<H, RestfulLoginConfigurer<H>, RestfulAuthenticationFilter> {

    public RestfulLoginConfigurer() {
        super(new RestfulAuthenticationFilter(), null);
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    @Override
    public void init(H http) throws Exception {
        super.init(http);
    }


    @Override
    public void configure(H http) throws Exception {

        RestfulAuthenticationFilter authFilter = getAuthenticationFilter();

        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

//        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SessionAuthenticationStrategy sessionAuthenticationStrategy = http
                .getSharedObject(SessionAuthenticationStrategy.class);
        if (sessionAuthenticationStrategy != null) {
            authFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        }
        RememberMeServices rememberMeServices = http
                .getSharedObject(RememberMeServices.class);
        if (rememberMeServices != null) {
            authFilter.setRememberMeServices(rememberMeServices);
        }

        RestfulAuthenticationFilter restfulAuthenticationFilter = postProcess(authFilter);

        http.addFilterBefore(restfulAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
