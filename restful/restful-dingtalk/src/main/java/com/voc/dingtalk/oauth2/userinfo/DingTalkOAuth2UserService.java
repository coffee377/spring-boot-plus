package com.voc.dingtalk.oauth2.userinfo;

import com.voc.dingtalk.service.IDingTalkUserService;
import com.voc.restful.core.autoconfigure.json.IJson;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/22 09:29
 */
@Component
public class DingTalkOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final IDingTalkUserService dingtalkUserService;
    private final IJson json;

    public DingTalkOAuth2UserService(IDingTalkUserService dingtalkUserService, IJson json) {
        this.dingtalkUserService = dingtalkUserService;
        this.json = json;
    }

    @SneakyThrows
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String clientId = userRequest.getClientRegistration().getClientId();
        String clientSecret = userRequest.getClientRegistration().getClientSecret();
        String code = userRequest.getAdditionalParameters().get("code").toString();
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        Object userDetailInfos = dingtalkUserService.getUserDetailInfos(clientId, clientSecret, code);
        String json = this.json.serializer(userDetailInfos);
        Map deserializer = this.json.deserializer(json, Map.class);
        deserializer.put("username", "demo1");
        authorities.add(new OAuth2UserAuthority(deserializer));
        DefaultOAuth2User auth2User = new DefaultOAuth2User(authorities, deserializer, "username");
        return auth2User;
    }
}
