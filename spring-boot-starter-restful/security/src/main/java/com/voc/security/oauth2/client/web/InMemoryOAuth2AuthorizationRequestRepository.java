package com.voc.security.oauth2.client.web;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/22 11:53
 */
public class InMemoryOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private final Map<String, OAuth2AuthorizationRequest> authorizationRequests = new HashMap<>();

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        Assert.notNull(request, "request cannot be null");
        String stateParameter = this.getStateParameter(request);
        if (stateParameter == null) {
            return null;
        }
        return authorizationRequests.get(stateParameter);
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(request, "request cannot be null");
        Assert.notNull(response, "response cannot be null");
        if (authorizationRequest == null) {
            this.removeAuthorizationRequest(request, response);
            return;
        }
        String state = authorizationRequest.getState();
        Assert.hasText(state, "authorizationRequest.state cannot be empty");
        authorizationRequests.put(state, authorizationRequest);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        Assert.notNull(request, "request cannot be null");
        String stateParameter = this.getStateParameter(request);
        if (stateParameter == null) {
            return null;
        }
        return authorizationRequests.remove(stateParameter);
    }


    /**
     * Gets the state parameter from the {@link HttpServletRequest}
     *
     * @param request the request to use
     * @return the state parameter or null if not found
     */
    private String getStateParameter(HttpServletRequest request) {
        return request.getParameter(OAuth2ParameterNames.STATE);
    }

}
