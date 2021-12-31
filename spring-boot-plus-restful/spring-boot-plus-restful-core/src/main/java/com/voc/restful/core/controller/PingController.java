package com.voc.restful.core.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/17 09:57
 */
@RestController
@ConditionalOnProperty(prefix = "api.ping", name = "enable", havingValue = "true", matchIfMissing = true)
public class PingController {

    @GetMapping("${api.ping.path:ping}")

    public String ping() {
        return "pong";
    }
}
