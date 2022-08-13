package com.voc.boot.dict.json.jackson;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/09 14:08
 */
public class DictJackson2ObjectMapperBuilder implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        DictModule dictModule = new DictModule();
        builder.modules(dictModule);
    }


    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
