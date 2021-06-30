package com.voc.security.configurer;

import com.voc.api.Constants;
import com.voc.api.utils.CommonUtil;
import com.voc.security.SecurityProperties;
import com.voc.security.core.authentication.restful.RestfulAuthenticationFailureHandler;
import com.voc.security.core.authentication.restful.RestfulAuthenticationFilter;
import com.voc.security.core.authentication.restful.RestfulAuthenticationSuccessHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/02 11:50
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestfulLoginConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, RestfulLoginConfigurer<H>, RestfulAuthenticationFilter> {

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
//        this.registerAuthenticationEntryPoint(http, null);
//        this.successHandler(null);
//        this.failureHandler(null);
    }

    @Override
    public void configure(H http) throws Exception {
        RestfulAuthenticationFilter authFilter = getAuthenticationFilter();

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        authFilter.setAuthenticationManager(authenticationManager);

        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);

        AuthenticationSuccessHandler authenticationSuccessHandler = applicationContext.getBean(RestfulAuthenticationSuccessHandler.class);
        AuthenticationFailureHandler authenticationFailureHandler = applicationContext.getBean(RestfulAuthenticationFailureHandler.class);
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SecurityProperties securityProps = applicationContext.getBean(SecurityProperties.class);
        String loginProcessUrl = securityProps.getLoginProcessUrl();
        if (CommonUtil.isCustomProcessUrl(loginProcessUrl)) {
            authFilter.setFilterProcessesUrl(loginProcessUrl);
        }

        authFilter = postProcess(authFilter);

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
