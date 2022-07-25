package com.voc.boot.result;

import com.voc.boot.result.autoconfigure.ResultAutoConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/24 01:02
 */
@WebMvcTest
@WebAppConfiguration
@ContextConfiguration(classes = {ResultAutoConfiguration.class, ResultController.class})
public class ResultTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("全局返回包装字符串")
    void globalWrapperString() throws Exception {
        mockMvc.perform(get("/global/string"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.['data']").value("返回字符串"));
    }
}
