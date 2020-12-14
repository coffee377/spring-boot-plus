package com.voc.api.security.configurer;

import com.voc.api.Constants;
import com.voc.restful.core.props.LoginProperties;
import com.voc.api.security.authentication.RestfulAuthenticationFailureHandler;
import com.voc.api.security.authentication.RestfulAuthenticationFilter;
import com.voc.api.security.authentication.RestfulAuthenticationSuccessHandler;
import com.voc.api.utils.LoginUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
        super(new RestfulAuthenticationFilter(), Constants.DEFAULT_LOGIN_PROCESS_URL);
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    @Override
    public void configure(H http) throws Exception {

        RestfulAuthenticationFilter authFilter = getAuthenticationFilter();

        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
        RestfulAuthenticationSuccessHandler authenticationSuccessHandler = applicationContext.getBean(RestfulAuthenticationSuccessHandler.class);
        RestfulAuthenticationFailureHandler authenticationFailureHandler = applicationContext.getBean(RestfulAuthenticationFailureHandler.class);
        LoginProperties loginProps = applicationContext.getBean(LoginProperties.class);
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        if (LoginUtil.isCustomProcessUrl(loginProps)) {
            authFilter.setFilterProcessesUrl(loginProps.getProcessUrl());
        }
        authFilter = postProcess(authFilter);

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
