package com.voc.api.autoconfigure;

import com.voc.api.autoconfigure.cache.RedisCacheAutoConfiguration;
import com.voc.api.autoconfigure.json.JsonAutoConfiguration;
import com.voc.api.controller.ErrorPlusController;
import com.voc.api.response.ResultAdvice;
import com.voc.api.utils.SpringUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 12:38
 */
@Configuration
@Import({JsonAutoConfiguration.class, RedisCacheAutoConfiguration.class, ErrorPlusController.class,
        ResultAdvice.class, SpringUtil.class})
//@ConfigurationPropertiesScan("com.voc.api")
@EnableConfigurationProperties(ApiProperties.class)
public class ApiAutoConfiguration implements WebMvcConfigurer {

    @Resource
    private ApiProperties api;

}
