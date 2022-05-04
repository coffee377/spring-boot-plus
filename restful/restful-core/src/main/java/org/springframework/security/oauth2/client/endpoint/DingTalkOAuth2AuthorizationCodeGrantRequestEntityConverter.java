package org.springframework.security.oauth2.client.endpoint;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/03 17:04
 */
public class DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter extends OAuth2AuthorizationCodeGrantRequestEntityConverter {

    @Override
    public RequestEntity<DingTalkAuth> convert(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration();
        OAuth2AuthorizationExchange authorizationExchange = authorizationGrantRequest.getAuthorizationExchange();

        DingTalkAuth dingTalkAuth = new DingTalkAuth();

        dingTalkAuth.setClientId(clientRegistration.getClientId());
        dingTalkAuth.setClientSecret(clientRegistration.getClientSecret());

        dingTalkAuth.setGrantType(authorizationGrantRequest.getGrantType().getValue());

        if (AuthorizationGrantType.AUTHORIZATION_CODE.equals(clientRegistration.getAuthorizationGrantType())) {
            dingTalkAuth.setCode(authorizationExchange.getAuthorizationResponse().getCode());
        }

        if (AuthorizationGrantType.REFRESH_TOKEN.equals(clientRegistration.getAuthorizationGrantType())) {
            /* 获取存储的令牌 */
            dingTalkAuth.setRefreshToken("");
        }

        URI uri = UriComponentsBuilder
                .fromUriString(authorizationGrantRequest.getClientRegistration().getProviderDetails().getTokenUri())
                .build().toUri();
        return new RequestEntity<>(dingTalkAuth, headers, HttpMethod.POST, uri);
    }

    @Override
    protected MultiValueMap<String, String> createParameters(OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        ClientRegistration clientRegistration = authorizationCodeGrantRequest.getClientRegistration();
        OAuth2AuthorizationExchange authorizationExchange = authorizationCodeGrantRequest.getAuthorizationExchange();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("clientId", clientRegistration.getClientId());
        parameters.add("clientSecret", clientRegistration.getClientSecret());
        parameters.add("grantType", authorizationCodeGrantRequest.getGrantType().getValue());
        parameters.add("code", authorizationExchange.getAuthorizationResponse().getCode());

        if (AuthorizationGrantType.REFRESH_TOKEN.equals(clientRegistration.getAuthorizationGrantType())) {
            parameters.add("refreshToken", "");
        }

//        return parameters;
        return null;
    }

}
