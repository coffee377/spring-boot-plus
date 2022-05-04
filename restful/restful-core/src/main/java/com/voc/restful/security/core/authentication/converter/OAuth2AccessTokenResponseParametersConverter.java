package com.voc.restful.security.core.authentication.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2AccessToken => Map
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/04 13:13
 */
public class OAuth2AccessTokenResponseParametersConverter implements Converter<OAuth2AccessTokenResponse, Map<String, Object>> {
    @Override
    public Map<String, Object> convert(OAuth2AccessTokenResponse tokenResponse) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("accessToken", tokenResponse.getAccessToken().getTokenValue());
//        parameters.put("tokenType", tokenResponse.getAccessToken().getTokenType().getValue());
//        parameters.put("expiresIn", String.valueOf(getExpiresIn(tokenResponse)));
//        if (!CollectionUtils.isEmpty(tokenResponse.getAccessToken().getScopes())) {
//            parameters.put("scope", StringUtils.collectionToDelimitedString(tokenResponse.getAccessToken().getScopes(), " "));
//        }
        if (tokenResponse.getRefreshToken() != null) {
            parameters.put("refreshToken", tokenResponse.getRefreshToken().getTokenValue());
        }
        String idToken = (String) tokenResponse.getAdditionalParameters().get("id_token");
        if (StringUtils.hasText(idToken)) {
            parameters.put("idToken", idToken);
        }
//        if (!CollectionUtils.isEmpty(tokenResponse.getAdditionalParameters())) {
//            for (Map.Entry<String, Object> entry : tokenResponse.getAdditionalParameters().entrySet()) {
//                parameters.put(entry.getKey(), entry.getValue().toString());
//            }
//        }
        return parameters;
    }

    private long getExpiresIn(OAuth2AccessTokenResponse tokenResponse) {
        if (tokenResponse.getAccessToken().getExpiresAt() != null) {
            return ChronoUnit.SECONDS.between(Instant.now(), tokenResponse.getAccessToken().getExpiresAt());
        }
        return -1;
    }

}
