package com.voc.demo.api;

import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.voc.dingtalk.service.IDingTalkCredentialsService;
import com.voc.dingtalk.service.IDingTalkService;
import com.voc.dingtalk.service.IDingTalkUserService;
import com.voc.system.entity.impl.User;
import com.voc.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 15:24
 */
@Slf4j
@RestController
public class DemoController {

    @Resource
    IDingTalkService dingTalkService;

    @Resource
    IDingTalkUserService dingTalkUserService;

    @Resource
    IDingTalkCredentialsService dingTalkCredentialsService;

    @Resource
    IUserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ping')")
    public String home() {
        return "pong";
    }

    /**
     * 初始化用户
     */
    @PostMapping("/user/init/{app}")
    public void addDingTalkUser(@PathVariable(name = "app") String appName, @RequestBody List<String> userid) {
        userid.forEach(id -> initUser(appName, id));
    }

    private void initUser(String appName, String userid) {
        String accessToken = dingTalkCredentialsService.getAccessTokenByAppName(appName);
        OapiV2UserGetResponse.UserGetResponse info = dingTalkUserService.getUserDetailInfo(accessToken, userid, null);
        User user = new User();
        String jobNumber = info.getJobNumber();
        // 用户名默认工号，不存在使用手机号
        if (StringUtils.hasText(jobNumber)) {
            user.setJobNumber(jobNumber);
            user.setUsername(jobNumber);
        } else {
            user.setUsername(info.getMobile());
        }

        user.setPassword(info.getMobile()); //密码默认手机号
        user.setRealName(info.getName());
        user.setMobile(info.getMobile());
        user.setEmail(info.getEmail());
        user.setAvatar(info.getAvatar());

        userService.save(user, true);
    }
}
