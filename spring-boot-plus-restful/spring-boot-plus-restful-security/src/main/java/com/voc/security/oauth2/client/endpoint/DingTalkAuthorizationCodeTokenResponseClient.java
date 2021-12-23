package com.voc.security.oauth2.client.endpoint;

import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

import java.util.Collections;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/22 13:51
 */
public class DingTalkAuthorizationCodeTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        String code = authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getCode();
        return OAuth2AccessTokenResponse
                .withToken("not_need_token")
                .tokenType(OAuth2AccessToken.TokenType.BEARER)
                .additionalParameters(Collections.singletonMap("code", code))
                .build();
    }
}
