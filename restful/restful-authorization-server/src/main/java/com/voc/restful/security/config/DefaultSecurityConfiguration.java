package com.voc.restful.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/27 19:22
 */
@Configuration
public class DefaultSecurityConfiguration {
    private final PasswordEncoder passwordEncoder;

    public DefaultSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Order(10)
    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> {
                    requests
                            .antMatchers("/account/**").permitAll()
                            .anyRequest().authenticated();
                });
        http.csrf().disable();
        return http.build();
    }


}
