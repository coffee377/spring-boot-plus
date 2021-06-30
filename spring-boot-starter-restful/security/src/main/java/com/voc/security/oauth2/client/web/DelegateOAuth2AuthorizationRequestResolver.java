package com.voc.security.oauth2.client.web;

import com.voc.security.oauth2.ProviderName;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/22 09:33
 */
public class DelegateOAuth2AuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final String REGISTRATION_ID_URI_VARIABLE_NAME = "registrationId";
    private final OAuth2AuthorizationRequestResolver defaultOAuth2AuthorizationRequestResolver;
    private final AntPathRequestMatcher authorizationRequestMatcher;

    public DelegateOAuth2AuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository,
                                                      String authorizationRequestBaseUri) {
        this.defaultOAuth2AuthorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, authorizationRequestBaseUri);
        this.authorizationRequestMatcher = new AntPathRequestMatcher(authorizationRequestBaseUri + "/{" + REGISTRATION_ID_URI_VARIABLE_NAME + "}");
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        return rebuildOAuth2AuthorizationRequest(defaultOAuth2AuthorizationRequestResolver.resolve(request), request);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        return rebuildOAuth2AuthorizationRequest(defaultOAuth2AuthorizationRequestResolver.resolve(request, clientRegistrationId), request);
    }

    protected OAuth2AuthorizationRequest rebuildOAuth2AuthorizationRequest(OAuth2AuthorizationRequest oauth2AuthorizationRequest, HttpServletRequest request) {
        if (oauth2AuthorizationRequest == null) {
            return null;
        }
        OAuth2AuthorizationRequest.Builder builder = OAuth2AuthorizationRequest.from(oauth2AuthorizationRequest);
        Map<String, Object> additionalParameters = new HashMap<>(0);
        // https://oapi.dingtalk.com/connect/qrconnect?appid=APPID&response_type=code&scope=snsapi_login&state=STATE&redirect_uri=REDIRECT_URI
        String registrationId = resolveRegistrationId(request);
        if (ProviderName.DINGTALK.getRegistrationId().equals(registrationId)) {
            additionalParameters.put("appid", oauth2AuthorizationRequest.getClientId());
        } else {
            return oauth2AuthorizationRequest;
        }
        return builder.additionalParameters(additionalParameters).build();
    }

    private String resolveRegistrationId(HttpServletRequest request) {
        if (this.authorizationRequestMatcher.matches(request)) {
            return this.authorizationRequestMatcher.matcher(request).getVariables().get(REGISTRATION_ID_URI_VARIABLE_NAME);
        }
        return null;
    }
}
