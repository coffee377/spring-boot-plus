package com.voc.restful.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/27 19:22
 */
@Configuration
public class DefaultSecurityConfiguration {
    private final PasswordEncoder passwordEncoder;
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        /* 访问权限控制 */
//        http.authorizeRequests(
//                        requests -> {
//                            requests
//                                    .antMatchers(HttpMethod.OPTIONS).permitAll()
//                                    .anyRequest().authenticated();
//                        }
//                )
//                .formLogin();
//
//        return http.build();
//    }


    public DefaultSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @ConditionalOnMissingBean
    UserDetailsService users() {
        UserDetails demo = User.builder()
                .username("demo")
                .password(passwordEncoder.encode("123456"))
                .roles("DEMO")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(demo, admin);
    }
}
