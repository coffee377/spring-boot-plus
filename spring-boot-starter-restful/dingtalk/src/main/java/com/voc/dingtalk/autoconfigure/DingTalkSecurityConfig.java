package com.voc.dingtalk.autoconfigure;

/**
 * 每当配置一个 WebSecurityConfigurerAdapter 时 ,
 * {@link org.springframework.security.web.FilterChainProxy} 中就会添加一个新的
 * {@link org.springframework.security.web.SecurityFilterChain}
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/24 09:27
 */
//@Slf4j
//@Order(1)
//@Configuration
//public class DingTalkSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    public DingTalkSecurityConfig() {
//        super(true);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/login/dingtalk")
//                .apply(new DingTalkLoginConfigurer<>())
//                .and().authorizeRequests().anyRequest().permitAll();
//    }
//}
