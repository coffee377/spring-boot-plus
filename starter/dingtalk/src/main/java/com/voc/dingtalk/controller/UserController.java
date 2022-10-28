package com.voc.dingtalk.controller;

import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.voc.dingtalk.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:27
 */
@RestController("dingTalkUserController")
@RequestMapping("/dingtalk")
public class UserController {

    @Resource
    private UserService dingTalkUserService;

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/{appName}/scan")
    public Object login(@PathVariable(name = "appName") String appName,
                        @RequestParam("code") String code,
                        @RequestParam(required = false) String state) {
        Object userInfo = dingTalkUserService.getUserDetailInfo(appName, code);
        return userInfo;
    }

    @GetMapping("/user/info")
    public Object getUserInfo() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("/fdf/fd", String.class);

        OapiV2UserGetResponse.UserGetResponse info = dingTalkUserService.getUserDetailInfo("", "", "");
        return info;
    }
}
