//package com.voc.oauth2.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.FormHttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import com.voc.security.oauth2.client.dingtalk.DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter;
//import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
//import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
//import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
//import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
//import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
//import org.springframework.util.Assert;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestOperations;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.Map;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2022/05/03 22:12
// */
//@Slf4j
//public class DingTalkAuthorizationCodeTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
//    private final Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> requestEntityConverter =
//            new DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter();
//
//    private final RestOperations restOperations;
//
//    public DingTalkAuthorizationCodeTokenResponseClient() {
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
////        tokenResponseHttpMessageConverter.setTokenResponseConverter();
//        RestTemplate restTemplate = new RestTemplate(
//                Arrays.asList(new FormHttpMessageConverter(), mappingJackson2HttpMessageConverter,
//                        tokenResponseHttpMessageConverter));
//        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
//        this.restOperations = restTemplate;
//    }
//
//    @Override
//    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
//        Assert.notNull(authorizationGrantRequest, "authorizationCodeGrantRequest cannot be null");
//        RequestEntity<?> request = this.requestEntityConverter.convert(authorizationGrantRequest);
//
//        try {
//            ResponseEntity<?> exchange = this.restOperations.exchange(request, Map.class);
//            log.warn("{}", exchange);
//        } catch (RestClientException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//}
