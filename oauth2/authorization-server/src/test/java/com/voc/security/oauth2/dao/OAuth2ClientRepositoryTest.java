package com.voc.security.oauth2.dao;

import com.voc.security.autoconfigure.AuthorizationServerAutoConfiguration;
import com.voc.security.oauth2.entity.po.OAuth2Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import javax.annotation.Resource;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/13 21:21
 */
@SpringBootTest(classes = AuthorizationServerAutoConfiguration.class)
class OAuth2ClientRepositoryTest {

    @Resource
    OAuth2ClientRepository clientRepository;

    @Resource
    UserMapper userMapper;

    @Test
    void addClient() {
        RegisteredClient demoClient = RegisteredClient.withId("2")
                .clientId("demo")
                .clientName("演示客户端")
                .clientSecret("{noop}123456")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .redirectUri("http://127.0.0.1:9000/login/oauth2/code/dingtalk")
                .redirectUri("http://127.0.0.1:8090/callback")
                .scope(OidcScopes.OPENID).build();

        OAuth2Client client =  OAuth2Client.from(demoClient);
        clientRepository.insert(client);

//        User user = new User();
//        user.setName("coffee377");
//        user.setAge(32);
//        user.setEmail("coffee377@dingtalk.com");
//        List<OAuth2ClientAuthenticationMethod> set = Arrays.asList(OAuth2ClientAuthenticationMethod.values());
//        user.setAuthenticationMethods(set);
//        user.setStatus(UsingStatus.LOCK);

//        userMapper.insert(user);
//
//        User user1 = userMapper.selectById("1591804921398206466");
//        System.out.println(user1);
    }

}
