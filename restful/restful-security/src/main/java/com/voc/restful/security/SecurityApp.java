package com.voc.restful.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/27 18:57
 */
@SpringBootApplication(scanBasePackages = "com.voc")
public class SecurityApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class, args);
    }
}
