package com.voc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/16 14:52
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, SecurityAutoConfiguration.class,
        UserDetailsServiceAutoConfiguration.class})
public class CoreApp {
    public static void main(String[] args) {
        SpringApplication.run(CoreApp.class, args);
    }
}
