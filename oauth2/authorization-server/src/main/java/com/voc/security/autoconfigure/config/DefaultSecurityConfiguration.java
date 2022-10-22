package com.voc.security.autoconfigure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                .csrf().disable();
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
