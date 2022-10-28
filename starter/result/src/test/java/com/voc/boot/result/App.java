package com.voc.boot.result;

import net.jqsoft.result.EnableResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/26 23:52
 */
@SpringBootApplication
@EnableResult
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

}
