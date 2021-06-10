package com.voc.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/25 12:01
 */
//@RestController
@RequestMapping({"${api.system.prefix:}/user"})
public class UserController {

//    @Resource
//    private IUserService userService;
//
//    @PostMapping("/login")
//    public String login(@RequestBody Account account) {
//        String username = account.getUsername();
//        String password = account.getPassword();
//        if (!StringUtils.hasText(username)) {
//            throw new BizException(1234, "用户名不能为空");
//        }
//        if (!password.equals("123456")) {
//            throw new BizException(BaseBizStatus.BAD_CREDENTIALS);
//        }
//        return "ABC";
//    }
//
//    @PostMapping
//    public String add(@RequestBody User user) {
//        return userService.add(user);
//    }
//
//    @DeleteMapping("{uid}")
//    public Result delete(@PathVariable String uid) {
//        userService.deleteById(uid);
//        return Result.success();
//    }

}
