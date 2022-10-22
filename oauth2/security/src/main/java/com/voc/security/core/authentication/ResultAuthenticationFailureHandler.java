package com.voc.security.core.authentication;

import com.voc.boot.result.Result;
import com.voc.boot.result.response.impl.ResultResponseHandler;
import com.voc.common.api.biz.InternalBizStatus;
import com.voc.security.core.expection.AuthorizationCodeException;
import com.voc.security.core.expection.UnboundUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
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
public class ResultAuthenticationFailureHandler extends ResultResponseHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (log.isErrorEnabled()) {
            log.error("用户登录失败 - {}", exception.getMessage());
        }

        String canonicalName = exception.getClass().getCanonicalName();
        log.warn("onAuthenticationFailure AuthenticationException is => {}", canonicalName);

        if (exception instanceof ProviderNotFoundException) {
            setBizStatus(InternalBizStatus.AUTHENTICATION_PROVIDER_NOT_FOUND);
        } else if (exception instanceof UnboundUserException) {
            setBizStatus(InternalBizStatus.UNBOUND_USER);
        } else if (exception instanceof AuthorizationCodeException) {
            setBizStatus(InternalBizStatus.INCORRECT_TEMPORARY_AUTHORIZATION_CODE);
        } else if (exception instanceof InvalidBearerTokenException) {
            setBizStatus(InternalBizStatus.INVALID_BEARER_TOKEN);
        } else if (exception instanceof UsernameNotFoundException) {
            setBizStatus(InternalBizStatus.USERNAME_NOT_FOUND);
        } else if (exception instanceof BadCredentialsException) {
            setBizStatus(InternalBizStatus.INVALID_USERNAME_OR_PASSWORD);
        } else if (exception instanceof OAuth2AuthenticationException) {
            OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
            String msg = error.toString();
            setBizStatus(InternalBizStatus.OAUTH2_AUTHENTICATION_EXCEPTION.message(msg));
        } else {
            setResult(Result.failure(exception));
        }

        this.output(request, response);
    }

}
