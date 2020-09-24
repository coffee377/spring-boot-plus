package com.voc.api.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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
 * @time 2017/12/27 18:01
 */
@Component
@Slf4j
public class RestfulAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (log.isInfoEnabled()) {
            log.info("用户登录失败 - {}", exception.getMessage());
        }
//		if (AjaxUtils.isAjax(request)) {
//			Result failure = null;
//			if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
//				failure = Result.failure(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
//			} else if (exception instanceof SessionAuthenticationException) {
//				failure = Result.failure("111", exception.getMessage());
//			}
//			String result = JSON.toJSONString(failure);
//			if (log.isDebugEnabled()) {
//				log.debug("响应 JSON 数据为：{}", result);
//			}
//			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//			response.getWriter().write(result);
//		} else {
//			/*登陆失败时重定向的地址*/
//			super.setUseForward(false);
//			super.setDefaultFailureUrl("/");
//			super.onAuthenticationFailure(request, response, exception);
//		}
    }
}
