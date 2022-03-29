package com.voc.restful.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/27 19:22
 */
@EnableWebSecurity
public class DefaultSecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /* 访问权限控制 */
        http.authorizeRequests(
                        requests -> {
                            requests
                                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .formLogin();

        return http.build();
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    UserDetailsService users() {
        UserDetails user = User.builder()
                .username("demo")
                .password(passwordEncoder().encode("123456"))
                .roles("DEMO")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
