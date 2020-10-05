package com.voc.api.security.authentication;

import com.voc.api.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
@Component
public class RestfulAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 事件发布
     */
    @Resource
    private ApplicationEventPublisher publisher;

//    @Resource
//    private ClientDetailsService clientDetailsService;
//
//    @Resource
//    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Resource
    private OAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        publisher.publishEvent(new LogInSuccessEvent(authentication, "用户登录成功"));
        Object result = authentication;
        if (authentication instanceof OAuth2AuthenticationToken) {
            String registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(registrationId)
                    .principal(authentication)
                    .attributes(attrs -> {
                        attrs.put(HttpServletRequest.class.getName(), request);
                        attrs.put(HttpServletResponse.class.getName(), response);
                    })
                    .build();
            OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
            assert authorizedClient != null;
            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();
            result = accessToken;
        } else {
//            Certification certification = (Certification) request.getAttribute("certification");

//            String clientId = certification.getUsername();
//            String clientSecret = certification.getPassword();
        }
//        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("github")
//                .principal(authentication)
//                .attributes(attrs -> {
//                    attrs.put(HttpServletRequest.class.getName(), request);
//                    attrs.put(HttpServletResponse.class.getName(), response);
//                })
//                .build();
//        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
//
//        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

//        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
//
//        if (clientDetails == null) {
//            throw new UnapprovedClientAuthenticationException("clientId 对应的配置信息不存在");
//        } else if (!clientDetails.getClientSecret().equals(clientSecret)){
//            throw new UnapprovedClientAuthenticationException("clientSecret 不匹配");
//        }
//
//        TokenRequest tokenRequest = new TokenRequest(Collections.emptyMap(), clientId, clientDetails.getScope(), "custom");
//
//        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
//
//        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
//
//        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
//

//        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            result = authentication;
//        }
        String res = Result.success(result).toString();
        if (log.isDebugEnabled()) {
            log.debug("响应 JSON 数据为：{}", res);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(res);
    }

    private String genToken(Authentication authentication) {
        return "123";
    }

}
