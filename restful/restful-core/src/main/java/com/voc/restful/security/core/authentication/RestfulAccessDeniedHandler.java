package com.voc.restful.security.core.authentication;

import com.voc.restful.core.response.Result;
import com.voc.restful.core.response.impl.InternalBizStatus;
import com.voc.restful.core.response.impl.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
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
public class RestfulAccessDeniedHandler extends ResponseHandler implements AccessDeniedHandler {

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
        if (e.getClass().isAssignableFrom(AccessDeniedException.class)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                if (log.isInfoEnabled()) {
                    log.info("{} attempted to access the protected URL: {}", auth.getName(), request.getRequestURI());
                }
            }
            setBizStatus(InternalBizStatus.FORBIDDEN);
        } else {
            setResult(Result.failure(e));
        }

        this.write(response);
    }
}

