package com.voc.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 15:24
 */
@Slf4j
@RestController
public class DemoController {

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ping')")
    public String home() {
        return "pong";
    }

}
