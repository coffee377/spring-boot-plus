package com.voc.boot.dict;

import com.voc.boot.dict.converter.EnumDictItemConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/02 23:47
 */
@Configuration
@ConditionalOnWebApplication
public class DictItemConverterConfiguration implements WebMvcConfigurer {

    /**
     * <p>默认 {@link org.springframework.core.convert.support.StringToEnumConverterFactory}</p>
     * <p>无法忽略大小写，不能多值转换</p>
     *
     * @param registry FormatterRegistry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        /* 枚举字典转换 */
        registry.addConverter(new EnumDictItemConverter());
        /* 持久化字典转换 */
    }
}
