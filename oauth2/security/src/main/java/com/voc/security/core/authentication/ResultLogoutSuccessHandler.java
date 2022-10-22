package com.voc.security.core.authentication;

import com.voc.boot.result.response.impl.ResultResponseHandler;
import com.voc.common.api.biz.InternalBizStatus;
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
public class ResultLogoutSuccessHandler extends ResultResponseHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("{} - 退出系统", authentication);
        }

        this.setBizStatus(InternalBizStatus.OK);
        this.output(request, response);
    }
}
