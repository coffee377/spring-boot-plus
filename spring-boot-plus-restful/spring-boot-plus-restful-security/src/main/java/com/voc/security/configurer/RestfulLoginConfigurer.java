package com.voc.security.configurer;

import com.voc.api.Constants;
import com.voc.api.utils.CommonUtil;
import com.voc.security.SecurityProperties;
import com.voc.security.core.authentication.restful.*;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/02 11:50
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestfulLoginConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, RestfulLoginConfigurer<H>, RestfulAuthenticationFilter> {

    private SecurityProperties securityProps;
    private AuthenticationEntryPoint authenticationEntryPoint;
    private AccessDeniedHandler accessDeniedHandler;
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;
    private LogoutSuccessHandler logoutSuccessHandler;

    public RestfulLoginConfigurer() {
        super(new RestfulAuthenticationFilter(), Constants.DEFAULT_LOGIN_PROCESS_URL);
    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    @Override
    public void init(H http) throws Exception {
        super.init(http);
        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
        securityProps = context.getBean(SecurityProperties.class);
        authenticationEntryPoint = context.getBean(RestfulAuthenticationEntryPoint.class);
        accessDeniedHandler = context.getBean(RestfulAccessDeniedHandler.class);
        authenticationSuccessHandler = context.getBean(RestfulAuthenticationSuccessHandler.class);
        authenticationFailureHandler = context.getBean(RestfulAuthenticationFailureHandler.class);
        logoutSuccessHandler = context.getBean(RestfulLogoutSuccessHandler.class);

    }

    @Override
    public void configure(H http) throws Exception {

        RestfulAuthenticationFilter authFilter = getAuthenticationFilter();

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        authFilter.setAuthenticationManager(authenticationManager);

        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        String loginProcessUrl = securityProps.getLoginProcessUrl();
        if (CommonUtil.isCustomProcessUrl(loginProcessUrl)) {
            authFilter.setFilterProcessesUrl(loginProcessUrl);
        }

        authFilter = postProcess(authFilter);

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        /* 注销处理 */
//        this.logout((HttpSecurity) http);

    }


//    private void logout(HttpSecurity http) throws Exception {
//        http.logout(logoutConfigurer -> {
//            logoutConfigurer.addLogoutHandler(logoutHandler);
//            logoutConfigurer.clearAuthentication(false);
//            logoutConfigurer.logoutSuccessHandler(logoutSuccessHandler);
//            String logoutProcessUrl = securityProps.getLogoutProcessUrl();
//            if (CommonUtil.isCustomProcessUrl(logoutProcessUrl)) {
//                logoutConfigurer.logoutUrl(logoutProcessUrl);
//            }
//        });
//    }
}
