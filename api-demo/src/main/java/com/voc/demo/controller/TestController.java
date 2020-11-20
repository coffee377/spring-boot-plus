package com.voc.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 10:43
 */
@RestController
public class TestController {

    @GetMapping
    public String index() {
        return "首页";
    }

}
