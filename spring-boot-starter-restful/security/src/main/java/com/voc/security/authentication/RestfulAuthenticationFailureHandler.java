package com.voc.security.authentication;

import com.voc.restful.core.response.BaseBizStatus;
import com.voc.restful.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 18:01
 */
@Slf4j
public class RestfulAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (log.isErrorEnabled()) {
            log.error("用户登录失败 - {}", exception.getMessage());
        }

        Result failure = Result.failure(exception);
        if (exception instanceof InvalidBearerTokenException) {
            failure = Result.failure(BaseBizStatus.INVALID_BEARER_TOKEN);
        } else if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            failure = Result.failure(BaseBizStatus.INVALID_USERNAME_OR_PASSWORD);
        }

        response.setStatus(HttpStatus.BAD_REQUEST.value());

        String result = failure.toString();
        if (log.isDebugEnabled()) {
            log.debug("响应 JSON 数据为：{}", result);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(result);
    }
}
