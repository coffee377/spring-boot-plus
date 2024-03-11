package com.voc.boot.dict;

import com.voc.boot.dict.autoconfigure.DictAutoConfiguration;
import com.voc.boot.dict.json.DictJsonConfiguration;
import com.voc.boot.dict.json.jackson.DictItemSerializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 22:43
 */
//@WebMvcTest(DictController.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {
//        DictJsonConfiguration.class, MockMvcConfiguration.class,
//})
@Slf4j
@SpringJUnitWebConfig({DictJsonConfiguration.class, MockMvcConfiguration.class})
//@SpringBootTest
//@AutoConfigureMockMvc
class DictControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(new DictController()).build();
    }

    @Test
    void enumDictItem() throws Exception {
        mockMvc.perform(get("/dict/enum"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
//                .andExpect(jsonPath("$.['success']").value(true))
//                .andExpect(jsonPath("$.['data']").isEmpty())
        ;
    }

    @Test
    void dataDictItem() throws Exception {
        mockMvc.perform(get("/dict/data"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
//                .andExpect(jsonPath("$.['success']").value(true))
//                .andExpect(jsonPath("$.['data']").isEmpty());
    }

}
