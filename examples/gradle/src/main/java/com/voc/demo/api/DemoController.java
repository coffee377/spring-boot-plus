package com.voc.demo.api;

import com.voc.demo.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 15:24
 */
@Slf4j
@RestController
public class DemoController {

    @Resource
    IDemoService demoService;

    @GetMapping("/hello/{content}")
    public String hello(@PathVariable String content) {
        log.warn("start web...");
        String result = demoService.hello(content);
        log.warn("end web...");
        return result;
    }

//    @GetMapping("/hello2/{content}")
//    public Mono<String> hello2(@PathVariable String content) {
//        log.warn("start webflux...");
//        Mono<String> mono = Mono.fromSupplier(() -> demoService.hello(content));
//        log.warn("end webflux...");
//        return mono;
//    }

}
