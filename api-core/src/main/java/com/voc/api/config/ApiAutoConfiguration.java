package com.voc.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 */
@Configuration
@EnableConfigurationProperties({ApiProperties.class})
public class ApiAutoConfiguration {

    @Resource
    private ApiProperties api;


//    @Bean
//    public Object fdf() {
//        return new Object();
//    }

}
