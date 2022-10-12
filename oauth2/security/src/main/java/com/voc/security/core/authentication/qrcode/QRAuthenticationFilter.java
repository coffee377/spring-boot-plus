package com.voc.security.core.authentication.qrcode;

import com.voc.security.core.authentication.AuthProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/23 14:13
 */
@Slf4j
public abstract class QRAuthenticationFilter extends AbstractAuthenticationProcessingFilter implements ApplicationContextAware {
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String TEMP_AUTH_CODE_KEY = "code";

    private ApplicationContext applicationContext;
    private QRAuthenticationConverter authenticationConverter;


    public QRAuthenticationFilter(String processesUrl) {
        super(new AntPathRequestMatcher(processesUrl, "POST"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取认证提供商
     *
     * @return AppName
     * @see AuthProvider
     */
    public abstract AuthProvider getProvider();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, ServletException {
        AbstractAuthenticationToken authRequest = this.authenticationConverter.convert(request);
        Object details = authenticationDetailsSource.buildDetails(request);
        authRequest.setDetails(details);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    public void afterPropertiesSet() {
        if (this.getAuthenticationManager() == null) {
            AuthenticationManager authenticationManager = applicationContext.getBean(AuthenticationManager.class);
            this.setAuthenticationManager(authenticationManager);
        }
        super.afterPropertiesSet();
    }

}
