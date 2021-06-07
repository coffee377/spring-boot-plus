package com.voc.dingtalk.controller;

import com.voc.dingtalk.service.CredentialsService;
import com.voc.restful.core.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:27
 */
@RestController("dingtalkUserController")
@RequestMapping("/dingtalk")
public class UserController {

    @Resource
    private CredentialsService credentialsService;

    @GetMapping("/login")
    public Result<String> login(@RequestParam("authCode") String code) {
        // 获取access_token，注意正式代码要有异常流处理
        String accessToken = "";
//        服务端通过临时授权码获取授权用户的个人信息。
//
//        调用sns/getuserinfo_bycode接口获取授权用户的个人信息，详情请参考根据sns临时授权码获取用户信息。
//
//        注意 通过临时授权码Code获取用户信息，临时授权码只能使用一次。
//        根据unionid获取userid。
//
//        调用user/getbyunionid接口获取userid，详情请参考根据unionid获取用户信息。
//
//        说明 根据unionid获取userid，需要创建企业内部应用（小程序或微应用），使用内部应用的Appkey和AppSecret调用接口access_token。
//        根据userid获取用户详情。
//
//        调用user/get接口获取用户信息，详情请参考获取用户详情。
//
//        示例代码
        Result<String> success = Result.success("");
        Result<Integer> success1 = Result.success(2);
        Result<Object> success2 = Result.success();
        return success;
    }
}
