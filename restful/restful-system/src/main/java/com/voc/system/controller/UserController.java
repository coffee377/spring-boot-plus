package com.voc.system.controller;

import com.voc.system.entity.bo.UserBO;
import com.voc.system.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 12:01
 */
@RestController
@RequestMapping({"/user"})
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public Serializable add(@RequestBody UserBO user) {
        return userService.save(user);
    }

//    /**
//     * 获取当前登录用户信息（菜单、角色、权限等）
//     *
//     * @return User
//     */
//    @GetMapping("/info")
////    @PreAuthorize("isAuthenticated()")
//    public UserVO userInfo() {
////        String name = authentication.getName();
////        User user = userService.getUserByUsername(name);
//        UserVO userVO = new UserVO();
//        return userVO;
//    }
//
//    @DeleteMapping("{uid}")
//    public Result delete(@PathVariable String uid) {
//        userService.deleteById(uid);
//        return Result.success();
//    }

}
