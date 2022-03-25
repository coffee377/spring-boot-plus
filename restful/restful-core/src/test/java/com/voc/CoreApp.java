package com.voc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/16 14:52
 */
@SpringBootApplication(exclude = {})
public class CoreApp {
    public static void main(String[] args) {
        SpringApplication.run(CoreApp.class, args);
    }
}
