package com.voc.api.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voc.api.controller.Certification;
import com.voc.api.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支持异步请求
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 17:50
 */
@Slf4j
public class RestfulAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String REQUEST_METHOD = "POST";

    @Resource
    private ObjectMapper objectMapper;

    public RestfulAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/password", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals(REQUEST_METHOD)) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        ServletInputStream inputStream = request.getInputStream();
        Certification certification = objectMapper.readValue(inputStream, Certification.class);

//        request.setAttribute("certification", certification);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(certification.getUsername(),
                certification.getPassword().trim());

        // Allow subclasses to set the "details" property
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
