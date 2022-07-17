package com.voc.common.dict.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/02 23:47
 */
@Configuration
public class EnumDictItemConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        /* Controller 层参数自动转换为枚举 */
        registry.addConverter(new EnumDictItemConverter());
    }

}
