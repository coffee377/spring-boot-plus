package com.voc.demo;

import com.voc.restful.core.response.ResponseResult;
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
@SpringBootApplication(scanBasePackages = {})
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

    @GetMapping("/2")
    @ResponseResult(false)
    public Map<String, Object> home2() {
        return new HashMap<String, Object>(2) {{
            put("name", "WuYujie");
            put("age", 32);
            put("age2", 88);
        }};
    }

}
