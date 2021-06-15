//package com.voc.api.security.authentication;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2020/09/26 12:42
// */
//@Slf4j
//@Component
//public class DefaultOAuth2AuthorizationSuccessHandler implements OAuth2AuthorizationSuccessHandler {
//    @Override
//    public void onAuthorizationSuccess(OAuth2AuthorizedClient authorizedClient, Authentication principal, Map<String, Object> attributes) {
//        if (log.isDebugEnabled()) {
//            log.debug("{}", authorizedClient, principal, attributes);
//        }
//    }
//}
