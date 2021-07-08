package com.voc.system.controller;

import com.voc.system.entity.Social;
import com.voc.system.service.ISocialService;
import com.voc.system.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/08 14:47
 */
@RestController
@RequestMapping({"${api.system.prefix:}/social"})
public class SocialController {

    @Resource
    ISocialService socialService;

    @Resource
    IUserService userService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    String bind(Authentication authentication, @RequestBody Social social) {
        String name = authentication.getName();
        String uid = userService.getUserByUsername(name).getId();
        social.setUid(uid);
        return socialService.bindUser(social);
    }

}
