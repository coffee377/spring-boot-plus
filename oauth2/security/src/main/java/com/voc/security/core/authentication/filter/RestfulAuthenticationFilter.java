package com.voc.security.core.authentication.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 17:50
 */
@Slf4j
public class RestfulAuthenticationFilter extends AbstractAuthenticationProcessingFilter implements ApplicationContextAware {
    public static String DEFAULT_LOGIN_PROCESS_URL = "/login";
    public static final String REQUEST_METHOD = "POST";

    private ApplicationContext applicationContext;
    private AuthenticationConverter authenticationConverter;
    private final Converter<HttpServletRequest, UsernamePasswordAuthenticationToken> usernamePasswordAuthenticationTokenConverter = httpServletRequest -> {
        // TODO: 2022/10/12 14:46 获取用户名密码
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated("", "");
        return authenticationToken;
    };

    private static final RequestMatcher REQUEST_MATCHER = new AndRequestMatcher(
            new AntPathRequestMatcher(DEFAULT_LOGIN_PROCESS_URL, "POST"),
            new OrRequestMatcher(
                    /* JSON 提交 */
                    new RequestHeaderRequestMatcher(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE),
                    /* 表单提交 */
                    new RequestHeaderRequestMatcher(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            )
    );

    public RestfulAuthenticationFilter() {
        super(REQUEST_MATCHER);
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

        UsernamePasswordAuthenticationToken authRequest = this.usernamePasswordAuthenticationTokenConverter.convert(request);
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
