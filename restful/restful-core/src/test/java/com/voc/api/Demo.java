package com.voc.api;

import com.voc.restful.core.response.Result;
import com.voc.restful.core.response.impl.InternalBizStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/16 14:42
 */
@RequestMapping("/demo")
@RestController
public class Demo {

    @GetMapping("/success")
    public Result<String> success() {
        return Result.success("ACCESS_TOKEN");
    }

    @GetMapping("/failure")
    public Result<String> failure() {
        return Result.failure(InternalBizStatus.ACCOUNT_EXISTS);
    }

    @GetMapping("/")
    public String ddd() {
        return "直接返回相应的数据";
    }

    @GetMapping("/user")
    public User user() {
        return new User("吴玉杰", 32, LocalDate.now(), Instant.now(), null);
    }


}
