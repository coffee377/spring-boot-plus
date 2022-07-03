package com.voc.dingtalk.controller;

import com.voc.dingtalk.autoconfigure.App;
import com.voc.dingtalk.service.IAppService;
import com.voc.dingtalk.service.IDingTalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 21:00
 */
@Slf4j
@RestController("dingTalkController")
@RequestMapping("/dingtalk")
public class DingTalkController {

    @Resource
    private IDingTalkService dingTalkService;

    @Resource
    private IAppService appService;

    @GetMapping("/app")
    public List<App> getApps() {
        return dingTalkService.getApps();
    }

    /**
     * 获取应用访问令牌
     *
     * @param appNameOrAppId 应用ID或应用名称
     * @return 应用访问令牌
     */
    @GetMapping("/{app}/access_token")
    public String getAccessToken(@PathVariable(value = "app") String appNameOrAppId) {
        return appService.getAccessToken(appNameOrAppId);
    }


}
