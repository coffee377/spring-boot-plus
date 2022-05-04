package com.voc.restful.security.core.authentication.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * OAuth2AccessToken => Map
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/04 13:13
 */
public class OAuth2AccessTokenResponseConverter implements Converter<Map<String, Object>, OAuth2AccessTokenResponse> {
    private static final Set<String> TOKEN_RESPONSE_PARAMETER_NAMES = new HashSet<>(
            Arrays.asList(OAuth2ParameterNames.ACCESS_TOKEN, OAuth2ParameterNames.EXPIRES_IN,
                    OAuth2ParameterNames.REFRESH_TOKEN, OAuth2ParameterNames.SCOPE, OAuth2ParameterNames.TOKEN_TYPE));

    @Override
    public OAuth2AccessTokenResponse convert(Map<String, Object> tokenResponseParameters) {
        String accessToken = (String) tokenResponseParameters.get(OAuth2ParameterNames.ACCESS_TOKEN);
        OAuth2AccessToken.TokenType accessTokenType = getAccessTokenType(tokenResponseParameters);
        long expiresIn = getExpiresIn(tokenResponseParameters);
        Set<String> scopes = getScopes(tokenResponseParameters);
        String refreshToken = (String) tokenResponseParameters.get(OAuth2ParameterNames.REFRESH_TOKEN);
        Map<String, Object> additionalParameters = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : tokenResponseParameters.entrySet()) {
            if (!TOKEN_RESPONSE_PARAMETER_NAMES.contains(entry.getKey())) {
                additionalParameters.put(entry.getKey(), entry.getValue());
            }
        }
        return OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(accessTokenType)
                .expiresIn(expiresIn)
                .scopes(scopes)
                .refreshToken(refreshToken)
                .additionalParameters(additionalParameters)
                .build();
    }

    private OAuth2AccessToken.TokenType getAccessTokenType(Map<String, Object> tokenResponseParameters) {
        if (OAuth2AccessToken.TokenType.BEARER.getValue()
                .equalsIgnoreCase((String) tokenResponseParameters.get(OAuth2ParameterNames.TOKEN_TYPE))) {
            return OAuth2AccessToken.TokenType.BEARER;
        }
        return null;
    }

    private long getExpiresIn(Map<String, Object> tokenResponseParameters) {
        if (tokenResponseParameters.containsKey(OAuth2ParameterNames.EXPIRES_IN)) {
            try {
                return Long.parseLong((String) tokenResponseParameters.get(OAuth2ParameterNames.EXPIRES_IN));
            } catch (NumberFormatException ex) {
            }
        }
        return 0;
    }

    private Set<String> getScopes(Map<String, Object> tokenResponseParameters) {
        if (tokenResponseParameters.containsKey(OAuth2ParameterNames.SCOPE)) {
            String scope = (String) tokenResponseParameters.get(OAuth2ParameterNames.SCOPE);
            return new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }
        return Collections.emptySet();
    }
}
