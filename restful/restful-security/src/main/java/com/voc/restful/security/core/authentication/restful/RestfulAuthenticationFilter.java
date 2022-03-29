package com.voc.restful.security.core.authentication.restful;

import com.voc.api.utils.RequestUtil;
import com.voc.restful.core.autoconfigure.json.IJson;
import com.voc.restful.core.entity.impl.Certification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支持异步请求
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 17:50
 */
@Slf4j
public class RestfulAuthenticationFilter extends AbstractAuthenticationProcessingFilter implements ApplicationContextAware {
    public static String DEFAULT_LOGIN_PROCESS_URL = "/login";
    public static final String REQUEST_METHOD = "POST";

    private ApplicationContext applicationContext;

    public RestfulAuthenticationFilter() {
        super(new AntPathRequestMatcher(DEFAULT_LOGIN_PROCESS_URL, "POST"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(REQUEST_METHOD)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        Certification certification;
        try {
            ServletInputStream inputStream = request.getInputStream();
            IJson json = applicationContext.getBean(IJson.class);
            certification = json.deserializer(inputStream, Certification.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException("获取认证数据失败");
        }

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(certification.getUsername(), certification.getPassword().trim());

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return RequestUtil.isRestfulRequest(request) && super.requiresAuthentication(request, response);
    }
}
