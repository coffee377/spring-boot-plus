package com.voc.boot.result;

import net.jqsoft.result.EnableCompatibilityResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/26 23:52
 */
@SpringBootApplication
@EnableCompatibilityResult
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

}
