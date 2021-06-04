package com.voc.demo.api;

import com.voc.demo.entity.User;
import com.voc.demo.service.UserService;
import com.voc.restful.core.response.ResponseResult;
import com.voc.restful.core.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 15:24
 */
@Slf4j
@RestController
@RequestMapping("/result")
public class ResultController {

    @Resource
    private UserService userService;

    /**
     * 直接返回需要的类型，返回结果会自动包装为相应的 Result 类型
     *
     * @return User
     */
    @GetMapping
    public User direct() {
        User user = userService.findById("1");
        log.warn("user => {} ", user.hashCode());
        return user;
    }

    /**
     * 返回结果写成相应的包装类型也是可以的
     *
     * @return Result<User>
     */
    @GetMapping("/result")
    public Result<User> result() {
        User user = userService.findById("1");
        log.warn("user => {} ", user.hashCode());
        return Result.success(user);
    }

    @GetMapping("list")
    public List<User> list() {
        return userService.findList(10);
    }

    @GetMapping("/no_data")
    public void noData() {
        userService.success();
    }

    @GetMapping("/no_data2")
    public Result noData2() {
        return Result.success();
    }

    /**
     * 局部关闭响应结果的包装
     *
     * @return User
     */
    @GetMapping("/no_wrapper")
    @ResponseResult(false)
    public User noWrapper() {
        User user = userService.findById("1");
        log.warn("user => {} ", user.hashCode());
        return user;
    }

}
