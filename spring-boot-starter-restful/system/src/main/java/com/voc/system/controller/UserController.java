package com.voc.system.controller;

import com.voc.system.entity.impl.User;
import com.voc.system.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 12:01
 */
@RestController
@RequestMapping({"${api.system.prefix:}/user"})
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public String add(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * 获取当前登录用户信息（菜单、角色、权限等）
     *
     * @return User
     */
    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    public User userInfo(Authentication authentication) {
        String name = authentication.getName();
        return userService.getUserByUsername(name);
    }

//    @DeleteMapping("{uid}")
//    public Result delete(@PathVariable String uid) {
//        userService.deleteById(uid);
//        return Result.success();
//    }

}
