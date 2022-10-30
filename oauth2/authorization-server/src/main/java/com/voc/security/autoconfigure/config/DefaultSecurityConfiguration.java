package com.voc.security.autoconfigure.config;

import com.voc.security.core.authentication.converter.OAuth2AccessTokenResponseConverter;
import com.voc.security.oauth2.DingTalkAuthCodeConvertFilter;
import com.voc.security.oauth2.DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter;
import com.voc.security.oauth2.client.OAuth2UserRequestEntityConverterPlus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/27 19:22
 */
@Configuration
public class DefaultSecurityConfiguration {

    @Bean
    @Order(10)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((authorize) -> {
                    authorize
//                            .antMatchers("/account/**").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin().and()
                .httpBasic().and()
                .oauth2Login(loginConfigurer -> {
                    loginConfigurer.authorizationEndpoint(authorizationEndpoint -> {
                    });

                    /* 令牌端点配置 */
                    loginConfigurer.tokenEndpoint(tokenEndpoint -> {
                        DefaultAuthorizationCodeTokenResponseClient authorizationCodeTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
                        authorizationCodeTokenResponseClient.setRequestEntityConverter(new DingTalkOAuth2AuthorizationCodeGrantRequestEntityConverter());
                        OAuth2AccessTokenResponseHttpMessageConverter responseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
                        responseHttpMessageConverter.setAccessTokenResponseConverter(new OAuth2AccessTokenResponseConverter());
                        RestTemplate restTemplate = new RestTemplate(Arrays.asList(
                                new FormHttpMessageConverter(),
                                responseHttpMessageConverter,
                                new MappingJackson2HttpMessageConverter()
                        ));
                        authorizationCodeTokenResponseClient.setRestOperations(restTemplate);
                        tokenEndpoint.accessTokenResponseClient(authorizationCodeTokenResponseClient);
                    });
                    /* 用户信息端点配置 */
                    loginConfigurer.userInfoEndpoint(userInfoEndpoint -> {
//                        userInfoEndpoint.customUserType(DingTalkOAuth2User.class, "dingtalk");
                        DefaultOAuth2UserService userService = new DefaultOAuth2UserService();
                        /* 请求参数转换 */
                        userService.setRequestEntityConverter(new OAuth2UserRequestEntityConverterPlus());
                        userInfoEndpoint.userService(userService);
                    });
                })
                .oauth2Client(clientConfigurer -> {
                    clientConfigurer.authorizationCodeGrant(authorizationCodeGrantConfigurer -> {
//                        authorizationCodeGrantConfigurer.
                    });
//                    clientConfigurer.
                })
                .csrf().disable();

        DingTalkAuthCodeConvertFilter dingTalkAuthCodeConvertFilter = new DingTalkAuthCodeConvertFilter();
        http.addFilterBefore(dingTalkAuthCodeConvertFilter, OAuth2LoginAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123456")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }


}
