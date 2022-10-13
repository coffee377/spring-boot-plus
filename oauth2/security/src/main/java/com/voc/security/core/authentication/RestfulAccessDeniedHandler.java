package com.voc.security.core.authentication;

import com.voc.boot.result.Result;
import com.voc.boot.result.response.impl.ResultResponseHandler;
import com.voc.common.api.biz.InternalBizStatus;
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
public class RestfulAccessDeniedHandler extends ResultResponseHandler implements AccessDeniedHandler {

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
        this.output(request, response);
    }
}

