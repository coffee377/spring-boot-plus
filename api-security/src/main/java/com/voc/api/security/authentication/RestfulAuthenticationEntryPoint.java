package com.voc.api.security.authentication;

import com.voc.api.response.BaseBizError;
import com.voc.api.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/19 14:11
 */
@Slf4j
@Component
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {

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
        Result failure = Result.failure(BaseBizError.UNAUTHORIZED);
        if (log.isDebugEnabled()) {
            log.debug("响应 JSON 数据为：{}", failure.toString());
        }
        response.getWriter().write(failure.toString());

    }

}
