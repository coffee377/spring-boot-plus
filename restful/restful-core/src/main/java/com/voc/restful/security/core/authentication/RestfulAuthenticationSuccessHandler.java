package com.voc.restful.security.core.authentication;

import com.voc.restful.core.response.Result;
import com.voc.restful.core.response.impl.ResponseHandler;
import com.voc.restful.security.core.authentication.converter.OAuth2AccessTokenResponseParametersConverter;
import com.voc.restful.security.core.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Map;

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

    private final Converter<OAuth2AccessTokenResponse, Map<String, Object>> accessTokenResponseParametersConverter = new OAuth2AccessTokenResponseParametersConverter();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AccessTokenResponse tokenResponse = genToken(authentication);

        publisher.publishEvent(new LoginSuccessEvent("用户登录成功", authentication, tokenResponse));

        Map<String, Object> map = accessTokenResponseParametersConverter.convert(tokenResponse);
        Result result = Result.success(map);

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
    private OAuth2AccessTokenResponse genToken(Authentication authentication) {
        if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
            return token4OAuth2(authentication);
        }
        // TODO: 2022/5/4 12:23 其他模式令牌生成
        return null;
    }

    @NotNull
    private OAuth2AccessTokenResponse token4OAuth2(Authentication authentication) {
        OAuth2AccessTokenAuthenticationToken accessTokenAuthentication = (OAuth2AccessTokenAuthenticationToken) authentication;

        OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
        OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
        Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();

        OAuth2AccessTokenResponse.Builder builder =
                OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
                        .tokenType(accessToken.getTokenType())
                        .scopes(accessToken.getScopes());
        if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
            builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
        }
        if (refreshToken != null) {
            builder.refreshToken(refreshToken.getTokenValue());
        }
        if (!CollectionUtils.isEmpty(additionalParameters)) {
            builder.additionalParameters(additionalParameters);
        }
        return builder.build();
    }
}
