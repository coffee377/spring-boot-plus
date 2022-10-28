package com.voc.dingtalk;

import net.jqsoft.result.EnableResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/31 21:28
 */
@EnableResult
@SpringBootApplication
public class DingTalkApp {
    public static void main(String[] args) {
        SpringApplication.run(DingTalkApp.class, args);
    }
}
