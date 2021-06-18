package com.voc.security.token.jwt;

import com.voc.api.controller.Certification;
import com.voc.restful.core.response.BaseBizStatus;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/16 17:02
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TokenControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JwtDecoder jwtDecoder;

    @Test
    @SneakyThrows
    @DisplayName("用户不存在")
    void notExistUser() {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"XXX\",\"password\": \"123456\"}"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(BaseBizStatus.INVALID_USERNAME_OR_PASSWORD.getCode()))
                .andExpect(jsonPath("$.message").value(BaseBizStatus.INVALID_USERNAME_OR_PASSWORD.getMessage()));
    }

    @Test
    @SneakyThrows
    @DisplayName("密钥凭证错误")
    void badCredentials() {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"admin\",\"password\": \"1234567\"}"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(BaseBizStatus.INVALID_USERNAME_OR_PASSWORD.getCode()))
                .andExpect(jsonPath("$.message").value(BaseBizStatus.INVALID_USERNAME_OR_PASSWORD.getMessage()));
    }

    @SneakyThrows
    @DisplayName("正常登陆")
    @Order(1)
    @ParameterizedTest
    @ValueSource(strings = {"admin", "test", "demo"})
    void login(String username) {
        Certification certification = new Certification();
        certification.setUsername(username);
        certification.setPassword("123456");
        String content = certification.toJson();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }

    @SneakyThrows
    @Order(2)
    @RepeatedTest(6)
    void refresh() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/token/refresh")
                        .param("access_token", "eyJraWQiOiJmZWxvcmRjbiIsInR5cCI6IkpXVCIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJkZW1vIiwiYXVkIjoiZGVtbyIsInNjb3BlIjpbInJlZnJlc2hfdG9rZW4iXSwiaXNzIjoiaHR0cHM6XC9cL3R2b2Muc2l0ZSIsImV4cCI6MTYyNjYxNTM4MCwiaWF0IjoxNjI0MDIzMzgwLCJqdGkiOiI4NGNlMGVhYy1hMGQ3LTQ5YzItYTAxZi03YjM4Njc0ZGUxNmQifQ.OGTABvVigmTn89MkIyzzhjKmVfoHZHQsIBUqQT7Zkg7J6xB5V6I8N5znwxtehkMwvptx5W1ZcGPSg1vfZ_TAIkGOReAAUZy01u105J5D40-iUEMwW0hA1Jzz7rVwwc8KHYzJuGBDWrIFux3RELObTVsAgRqv0biZXYYelSdxxhOZLTAgej3UGeO8ViukSdpQW7Yp9y7uJjTcLvl4Z9AuSwGkua4WN8hVpVI8Qf2H1q3PRbHHjfyt50zhdqS3gCn1eCEdJv67aqb58MiuhWsCFyPt7d7P7QIwt2d5zU_jOSCt9s8ZdZyR_KMfb_aerlHDgMLfg7FdmmVx5UtvYnatOA")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
        ;
    }

    @Test
    @SneakyThrows
    @DisplayName("下线所有用户")
    @WithMockUser(username = "admin", password = "123456", roles = {"ADMIN", "KICKING"})
    void offlineAll() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/token/offline")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings = "demo")
    @DisplayName("下线指定用户")
    @WithMockUser(username = "admin", password = "123456", roles = {"ADMIN", "KICKING"})
    void offline(String username) {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/token/offline/" + username)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings = "demo")
    @DisplayName("下线指定用户")
    @WithMockUser(username = "admin", password = "123456", roles = {"ADMIN", "KICKING"})
    void offlineBy(String username) {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/token/offline/" + username)
                        .content("[1624009859159,1624009859260,1624023510668]")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("过期的JWT Token 会抛出异常")
    void jwtDecode() {
        String expiredToken = "eyJraWQiOiJmZWxvcmRjbiIsInR5cCI6IkpXVCIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJkZW1vIiwiYXVkIjoiZGVtbyIsImlhdC1taWxsaXNlY29uZHMiOjE2MjQwMjgyMDk3ODYsInNjb3BlIjpbIlNDT1BFX2RlbW8iXSwiaXNzIjoiaHR0cHM6XC9cL3R2b2Muc2l0ZSIsImV4cCI6MTYyNDAyODUwOSwiaWF0IjoxNjI0MDI4MjA5LCJqdGkiOiJhNTBhMGZlNy01M2RhLTQzODgtYTBmZC01NDE0YTQxZDc3MTgifQ.CXZE5UayP6zDCL1DPdoaP6On7oU9bTvHCpy8wOvoCagKVtW-kC9laSV6vSJ596L0rEkwkBcwrVh7bcUsBtIEKdOP_EBGff91b8sqfsLBwhpe75F85f7LbCUvr8fw6qMauRous3yLfINqcheMiqp0tZ7Gl8hCrw0kDqrmrkNd3LhAXd1TDV4mb0vLeMMo-A7LW6CoxdcY8Sm9l8_qrIy4NYVB_BUtRYUKbTTai85ZzxEQqI-c29pOfLUgRMzBkUwyqoxpqAKeOfEj5KN2Ac1C9WuKioxfA0OOBJ7LOxF5VRC9a-fY-qtPcxi0Y-804_mRXOuJmJjO3LOX21DCY69XVg";
        Jwt decode = jwtDecoder.decode(expiredToken);
    }
}
