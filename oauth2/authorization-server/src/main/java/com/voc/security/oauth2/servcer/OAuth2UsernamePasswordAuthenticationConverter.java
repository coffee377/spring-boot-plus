package com.voc.security.oauth2.servcer;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/05 12:25
 */
public class OAuth2UsernamePasswordAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!AuthorizationGrantType.PASSWORD.getValue().equals(grantType)) return null;
        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
        String username = request.getParameter(OAuth2ParameterNames.USERNAME);
        String password = request.getParameter(OAuth2ParameterNames.PASSWORD);
        Map<String, Object> additionalParameters = new HashMap<>();
        return OAuth2UsernamePasswordAuthenticationToken.unauthenticated(username, password, clientPrincipal, additionalParameters);
    }

}
