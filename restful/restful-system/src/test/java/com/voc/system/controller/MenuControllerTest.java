package com.voc.system.controller;

import com.voc.boot.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/08 10:28
 */
@Slf4j
@DisplayName("菜单API测试")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MenuControllerTest {

    @Resource
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void add() {
    }

    @Test
    void addBatch() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteBatch() {
    }

    @Test
    void update() {
    }

    @Test
    void updateById() {
    }

    @Test
    void updateBatch() {
    }

    @Test
    @DisplayName("查询所有记录")
    void findAll() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/system/menu", Result.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Result body = response.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertEquals(body.getCode(), 0L);
        Assertions.assertTrue(body.getData() instanceof List);
        Assertions.assertTrue(((List<?>) body.getData()).size() > 0);
    }

    @Test
    @DisplayName("指定记录不存在")
    void findOne() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/system/menu/0", Result.class);
        Result body = response.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertFalse(body.isSuccess());
        Assertions.assertTrue(body.getCode() != 0L);
    }
}
