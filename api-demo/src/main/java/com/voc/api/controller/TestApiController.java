package com.voc.api.controller;

import com.voc.api.autoconfigure.json.IJson;
import com.voc.api.response.BaseBizError;
import com.voc.api.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 11:03
 */
@Slf4j
@RestController
@RequestMapping("${api.prefix:/api}")
public class TestApiController extends BaseController {

    @Resource
    private IJson json;

    @GetMapping("/test")
    public Result test() throws Exception {
        Demo demo = new Demo();
        demo.setName("李四");
        demo.setAge(30);
        demo.setInstant(Instant.now());
        demo.setBirthday(LocalDateTime.now());
        demo.setBirthday2(LocalDate.now());
        demo.setBirthday3(LocalTime.now());
        demo.setBirthday4(new Date());
        demo.setList(Collections.singletonList("demo"));
        HashMap<String, Object> hashMap = new HashMap<>(0);
        hashMap.put("name", "张三888");
        demo.setMap(hashMap);

        String result = json.serializer(demo);

        Demo demo1 = json.deserializer(result, Demo.class);

        log.warn("{}", demo1);
        return Result.success(demo);

    }

    @GetMapping("/test2")
    public Result test2() {
        return Result.failure(BaseBizError.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping("/oauth2/verify")
    public void verify(HttpServletRequest request, HttpServletResponse response) {
        log.warn("/oauth2/verify");
    }
}
