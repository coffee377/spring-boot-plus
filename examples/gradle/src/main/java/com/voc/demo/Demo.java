package com.voc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/09 21:20
 */
@SpringBootApplication(scanBasePackages = {"com.voc"})
@RestController
public class Demo {

    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);

    }

    @GetMapping
    public Map<String, Object> home() {
        return new HashMap<String, Object>(2) {{
            put("name", "WuYujie");
            put("age", 32);
        }};
    }

}
