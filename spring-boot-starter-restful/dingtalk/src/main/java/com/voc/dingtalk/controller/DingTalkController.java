package com.voc.dingtalk.controller;

import com.voc.restful.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 20:22
 */
@Slf4j
@RestController
@RequestMapping("/dingtalk")
public class DingTalkController {


    @GetMapping("/test")
    public String ddd() {
        return "测试";
    }

    @GetMapping("/test2")
    public Result<Object> ddd2() {
        return Result.builder().success("成功", "测试2").build();
    }

    @GetMapping("/test3")
    public Integer ddd3() {
        return 3;
    }

    @GetMapping("/test4")
    public Demo ddd4() {
        Demo demo = new Demo();
        demo.setBirthday4(new Date());
        demo.setAge(30);
        demo.setName("zs");
        demo.setB1(true);
        return demo;
    }


}
