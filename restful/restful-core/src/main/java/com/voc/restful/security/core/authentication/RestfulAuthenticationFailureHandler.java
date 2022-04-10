package com.voc.restful.security.core.authentication;

import com.voc.restful.core.response.Result;
import com.voc.restful.core.response.impl.BaseBizStatus;
import com.voc.restful.core.response.impl.ResponseHandler;
import com.voc.restful.security.core.expection.AuthorizationCodeException;
import com.voc.restful.security.core.expection.UnboundUserException;
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
public class RestfulAuthenticationFailureHandler extends ResponseHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (log.isErrorEnabled()) {
            log.error("用户登录失败 - {}", exception.getMessage());
        }

        String canonicalName = exception.getClass().getCanonicalName();
        log.warn("onAuthenticationFailure AuthenticationException is => {}", canonicalName);

        if (exception instanceof ProviderNotFoundException) {
            setBizStatus(BaseBizStatus.AUTHENTICATION_PROVIDER_NOT_FOUND);
        } else if (exception instanceof UnboundUserException) {
            setBizStatus(BaseBizStatus.UNBOUND_USER);
        } else if (exception instanceof AuthorizationCodeException) {
            setBizStatus(BaseBizStatus.INCORRECT_TEMPORARY_AUTHORIZATION_CODE);
        } else if (exception instanceof InvalidBearerTokenException) {
            setBizStatus(BaseBizStatus.INVALID_BEARER_TOKEN);
        } else if (exception instanceof UsernameNotFoundException) {
            setBizStatus(BaseBizStatus.USERNAME_NOT_FOUND);
        } else if (exception instanceof BadCredentialsException) {
            setBizStatus(BaseBizStatus.INVALID_USERNAME_OR_PASSWORD);
        } else if (exception instanceof OAuth2AuthenticationException) {
            OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
            String msg = error.toString();
            setBizStatus(BaseBizStatus.OAUTH2_AUTHENTICATION_EXCEPTION.message(msg));
        } else {
            setResult(Result.failure(exception));
        }

        this.write(response);
    }

}
