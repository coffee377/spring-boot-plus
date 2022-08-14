package com.voc.boot.dict;

import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/14 15:09
 */
@Configuration
public class MockMvcConfiguration implements MockMvcBuilderCustomizer {

    @Override
    public void customize(ConfigurableMockMvcBuilder<?> builder) {
        builder.alwaysDo(mvcResult -> {
            mvcResult.getResponse().setCharacterEncoding("UTF-8");
        });
    }

}
