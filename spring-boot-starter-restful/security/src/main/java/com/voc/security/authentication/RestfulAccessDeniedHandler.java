package com.voc.security.authentication;

import com.voc.restful.core.response.BaseBizStatus;
import com.voc.restful.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/01/29 16:44
 */
@Slf4j
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 权限异常处理
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param e        AccessDeniedException
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        Result failure;
        response.sendError(HttpStatus.FORBIDDEN.value(), e.getMessage());
        if (response.isCommitted()) {
            return;
        }
        if (e.getClass().isAssignableFrom(AccessDeniedException.class)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                if (log.isInfoEnabled()) {
                    log.info("{} attempted to access the protected URL: {}", auth.getName(), request.getRequestURI());
                }
            }
            failure = Result.failure(BaseBizStatus.FORBIDDEN);
        } else {
            failure = Result.failure(e);
        }

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        if (log.isDebugEnabled()) {
            log.debug("响应 JSON 数据为：{}", failure);
        }
        response.getWriter().write(failure.toString());
    }
}

