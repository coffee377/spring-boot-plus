package com.voc.system.controller;

import com.voc.system.service.ISocialService;
import com.voc.system.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/08 14:47
 */
@RestController
@RequestMapping({"/social"})
public class SocialController {

    @Resource
    ISocialService socialService;

    @Resource
    IUserService userService;

//    @PostMapping
//    @PreAuthorize("isAuthenticated()")
//    String bind(Authentication authentication, @RequestBody Social social) {
//        String name = authentication.getName();
//        String uid = userService.getUserByUsername(name).getId();
//        social.setUid(uid);
//        return socialService.bindUser(social);
//    }

}
