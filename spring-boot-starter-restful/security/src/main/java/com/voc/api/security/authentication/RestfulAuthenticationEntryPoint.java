package com.voc.api.security.authentication;

import com.voc.api.Constants;
import com.voc.restful.core.props.LoginProperties;
import com.voc.restful.core.response.BaseBizStatus;
import com.voc.restful.core.response.Result;
import com.voc.api.utils.LoginUtil;
import com.voc.api.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
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
public class RestfulAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public RestfulAuthenticationEntryPoint(LoginProperties loginProperties) {
        super(LoginUtil.isDefaultPage(loginProperties) ? Constants.DEFAULT_LOGIN_PAGE_URL : loginProperties.getPage());
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

        if (RequestUtil.isRestfulRequest(request)) {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Result failure = Result.failure(BaseBizStatus.UNAUTHORIZED);
            if (e instanceof InvalidBearerTokenException) {
                failure = Result.failure(BaseBizStatus.INVALID_BEARER_TOKEN);
            } else if (e instanceof UsernameNotFoundException) {
                failure = Result.failure(BaseBizStatus.USERNAME_NOT_FOUND);
            } else if (e instanceof BadCredentialsException) {
                failure = Result.failure(BaseBizStatus.BAD_CREDENTIALS);
            }
            if (log.isDebugEnabled()) {
                log.debug("响应 JSON 数据为：{}", failure.toString());
            }
            response.getWriter().write(failure.toString());
        } else {
            super.commence(request, response, e);
        }

    }

}
