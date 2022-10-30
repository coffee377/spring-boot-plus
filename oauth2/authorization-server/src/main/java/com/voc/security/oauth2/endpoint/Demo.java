package com.voc.security.oauth2.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/29 21:38
 */
@RestController
public class Demo {

    @GetMapping
    public String home(){
        return "home";
    }
}
