package com.voc.restful.security.core.authentication;

import com.voc.restful.core.response.Result;
import com.voc.restful.core.response.impl.ResponseHandler;
import com.voc.restful.security.core.authentication.token.model.TokenResult;
import com.voc.restful.security.core.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 17:58
 */
@Slf4j
public class RestfulAuthenticationSuccessHandler extends ResponseHandler implements AuthenticationSuccessHandler,
        ApplicationContextAware,
        ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    private ApplicationContext applicationContext;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        TokenResult tokenResult = this.genToken(authentication);
        publisher.publishEvent(new LoginSuccessEvent("用户登录成功", authentication, tokenResult));

        Result result;

        if (tokenResult.getRefreshToken() == null) {
            result = Result.success(tokenResult.getAccessToken());
        } else {
            result = Result.success(tokenResult);
        }

        this.setResult(result);
        this.write(response);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 生成用户令牌
     *
     * @param authentication Authentication
     * @return TokenResult
     */
    private @NonNull TokenResult genToken(Authentication authentication) {
        TokenResult tokenResult = new TokenResult();
        /* OAuth2 协议令牌转换为统一格式返回 */
        if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
            OAuth2AccessTokenAuthenticationToken oauth2AccessTokenAuthenticationToken =
                    (OAuth2AccessTokenAuthenticationToken) authentication;
//            Object client = authentication.getPrincipal();
//            log.debug("{}", client);
//            String clientId = authentication.getName();
//            log.debug("{}", clientId);
            OAuth2AccessToken accessToken = oauth2AccessTokenAuthenticationToken.getAccessToken();
            tokenResult.setAccessToken(accessToken.getTokenValue());
            OAuth2RefreshToken refreshToken = oauth2AccessTokenAuthenticationToken.getRefreshToken();
            if (refreshToken != null && refreshToken.getTokenValue() != null) {
                tokenResult.setRefreshToken(refreshToken.getTokenValue());
            }
        }
//        Object principal = authentication.getPrincipal();
//        UserDetails userDetails;
//        if (principal instanceof UserDetails) {
//            userDetails = (UserDetails) principal;
//        } else if (principal instanceof OAuth2ClientAuthenticationToken) {
////            principal.
//            String username = authentication.getName();
//            UserDetailsService userDetailsService = applicationContext.getBean(UserDetailsService.class);
//            try {
//                userDetails = userDetailsService.loadUserByUsername(username);
//            } catch (UsernameNotFoundException ignored) {
//                return null;
//            }
//        }

        return tokenResult;
//        TokenGenerator tokenGenerator = applicationContext.getBean(TokenGenerator.class);
//        TokenProperties tokenProperties = applicationContext.getBean(TokenProperties.class);
//        return tokenGenerator.create(Instant.now(), userDetails, !tokenProperties.isDualToken());
    }

}
