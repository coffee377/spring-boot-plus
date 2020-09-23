package com.voc.api.controller;

import com.voc.api.response.BaseBizError;
import com.voc.api.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/23 11:03
 */
@RestController
@RequestMapping("/api")
public class TestApiController {

    @GetMapping("/test")
    public Result test() {
        return Result.success("测试数据888888");
    }

    @GetMapping("/test2")
    public Result test2() {
        return Result.failure(BaseBizError.INTERNAL_SERVER_ERROR);
    }
}
