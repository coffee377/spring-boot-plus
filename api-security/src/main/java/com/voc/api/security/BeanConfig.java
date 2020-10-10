package com.voc.api.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/26 21:12
 */
//@Order(1)
@Configuration
public class BeanConfig {

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(
////                ClientRegistration.withClientRegistration(c)
//                ClientRegistration.withRegistrationId("github")
//                        .clientId("db3bd3536a1d3163acf9")
//                        .clientSecret("2b3b4ae1eaf3a9590feb5f01c84b694019c69ce0")
//                        .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
////                        .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
//                        .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
//                        .scope("read:user")
//                        .authorizationUri("https://github.com/login/oauth/authorize")
//                        .tokenUri("https://github.com/login/oauth/access_token")
//                        .userInfoUri("https://api.github.com/user")
//                        .userNameAttributeName("login")
//                        .clientName("GitHub")
//                        .build()
//        );
//    }

    @Bean
    public OAuth2AuthorizedClientService oauth2AuthorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
//        return new JdbcOAuth2AuthorizedClientService(clientRegistrationRepository);
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
//    }
//    @Bean
//    RestTemplate webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
//        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
//                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
//        return RestTemplate.builder()
//                .apply(oauth2Client.oauth2Configuration())
//                .build();
//    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .password()
                        .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        // Assuming the `username` and `password` are supplied as `HttpServletRequest` parameters,
        // map the `HttpServletRequest` parameters to `OAuth2AuthorizationContext.getAttributes()`
        authorizedClientManager.setContextAttributesMapper(contextAttributesMapper());

        return authorizedClientManager;
    }

    private Function<OAuth2AuthorizeRequest, Map<String, Object>> contextAttributesMapper() {
        return authorizeRequest -> {
            Map<String, Object> contextAttributes = Collections.emptyMap();
            HttpServletRequest servletRequest = authorizeRequest.getAttribute(HttpServletRequest.class.getName());
            assert servletRequest != null;
            String username = servletRequest.getParameter(OAuth2ParameterNames.USERNAME);
            String password = servletRequest.getParameter(OAuth2ParameterNames.PASSWORD);
            if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
                contextAttributes = new HashMap<>(2);

                // `PasswordOAuth2AuthorizedClientProvider` requires both attributes
                contextAttributes.put(OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME, username);
                contextAttributes.put(OAuth2AuthorizationContext.PASSWORD_ATTRIBUTE_NAME, password);
            }
            return contextAttributes;
        };
    }
}
