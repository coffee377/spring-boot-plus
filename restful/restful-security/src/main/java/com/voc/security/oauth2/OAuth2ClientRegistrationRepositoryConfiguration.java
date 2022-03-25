//package com.voc.security.oauth2;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.client.ClientsConfiguredCondition;
//import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2020/10/15 09:51
// */
//@Conditional(ClientsConfiguredCondition.class)
//@ConditionalOnClass({EnableWebSecurity.class, ClientRegistration.class})
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
//@EnableConfigurationProperties(OAuth2Properties.class)
//public class OAuth2ClientRegistrationRepositoryConfiguration {
//
//    private final OAuth2ClientProperties clientProperties;
//
//    public OAuth2ClientRegistrationRepositoryConfiguration(OAuth2ClientProperties clientProperties, OAuth2Properties configProperties) {
//        this.clientProperties = clientProperties;
//        String authorizationResponseBasePath = configProperties.getAuthorizationResponseBasePath();
//        if (!authorizationResponseBasePath.equals("/login/oauth2/code")) {
//            this.clientProperties.getRegistration().values().forEach(registration -> {
//                registration.setRedirectUri("{baseUrl}" + authorizationResponseBasePath + "/{registrationId}");
//            });
//        }
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(ClientRegistrationRepository.class)
//    InMemoryClientRegistrationRepository clientRegistrationRepository() {
//        List<ClientRegistration> registrations = new ArrayList<>(
//                OAuth2ClientRegistrationAdapter
//                        .getClientRegistrations(this.clientProperties).values());
//        return new InMemoryClientRegistrationRepository(registrations);
//    }
//
//}
