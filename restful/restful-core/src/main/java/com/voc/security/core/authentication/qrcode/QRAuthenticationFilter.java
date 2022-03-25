package com.voc.security.core.authentication.qrcode;

import com.voc.restful.core.autoconfigure.json.IJson;
import com.voc.restful.core.third.AppName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    public QRAuthenticationFilter(String processesUrl) {
        super(new AntPathRequestMatcher(processesUrl, "POST"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取扫码应用类型
     *
     * @return AppName
     * @see com.voc.restful.core.third.ThirdApp
     */
    public abstract AppName getAppType();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, ServletException {
        String clientId;
        String code;

        try {
            ServletInputStream inputStream = request.getInputStream();
            IJson json = applicationContext.getBean(IJson.class);

            Map map = json.deserializer(inputStream, Map.class);
            clientId = map.get(CLIENT_ID_KEY).toString();
            code = map.get(TEMP_AUTH_CODE_KEY).toString();

        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        String type = getAppType().get();
        QRAuthenticationToken authRequest = new QRAuthenticationToken(type, clientId, code);

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
