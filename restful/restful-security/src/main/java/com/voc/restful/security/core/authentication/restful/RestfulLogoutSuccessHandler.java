package com.voc.restful.security.core.authentication.restful;

import com.voc.restful.core.response.impl.BaseBizStatus;
import com.voc.restful.core.response.impl.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/25 09:12
 */
@Slf4j
public class RestfulLogoutSuccessHandler extends ResponseHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("{} - 退出系统", authentication);
        }

        this.setBizStatus(BaseBizStatus.OK);
    }
}
