package com.voc.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 */
@Configuration
@EnableConfigurationProperties({ApiProperties.class})
public class ApiAutoConfiguration implements WebMvcConfigurer {

    @Resource
    private ApiProperties api;


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
//        converters.add(gsonHttpMessageConverter);
    }
}
