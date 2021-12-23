package com.voc.security.oauth2.client.endpoint;

import com.voc.security.oauth2.ProviderName;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/22 13:58
 */
public class DelegateOAuth2AccessTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        String registrationId = authorizationGrantRequest.getClientRegistration().getRegistrationId();
        if (ProviderName.DINGTALK.getRegistrationId().equals(registrationId)) {
            return new DingTalkAuthorizationCodeTokenResponseClient().getTokenResponse(authorizationGrantRequest);
        }
        return new DefaultAuthorizationCodeTokenResponseClient().getTokenResponse(authorizationGrantRequest);
    }
}
