package com.voc.security.authentication;

import com.voc.restful.core.response.BaseBizStatus;
import com.voc.restful.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/19 14:11
 */
@Slf4j
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {

    private final String loginUrl;

    public RestfulAuthenticationEntryPoint(String loginUrl) {
        Assert.notNull(loginUrl, "loginUrl cannot be null");
        this.loginUrl = loginUrl;
    }

    /**
     * 认证异常处理
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param e        AuthenticationException
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (log.isErrorEnabled()) {
            log.info("访问资源 {} 需要用户身份认证", request.getRequestURL().toString());
        }

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Result failure = Result.failure(BaseBizStatus.UNAUTHORIZED);
        if (e instanceof InsufficientAuthenticationException) {
            failure = Result.failure(BaseBizStatus.UNAUTHORIZED);
        }
//            if (e instanceof InvalidBearerTokenException) {
//                failure = Result.failure(BaseBizStatus.INVALID_BEARER_TOKEN);
//            } else
        else if (e instanceof UsernameNotFoundException) {
            failure = Result.failure(BaseBizStatus.USERNAME_NOT_FOUND);
        } else if (e instanceof BadCredentialsException) {
            failure = Result.failure(BaseBizStatus.BAD_CREDENTIALS);
        }
        if (log.isDebugEnabled()) {
            log.debug("响应 JSON 数据为：{}", failure.toString());
        }
        response.getWriter().write(failure.toString());

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(StringUtils.hasText(this.loginUrl) && UrlUtils.isValidRedirectUrl(this.loginUrl),
                "loginUrl must be specified and must be a valid redirect URL");

    }
}
