package com.voc.dingtalk.controller;

import com.voc.dingtalk.entity.AppInfo;
import com.voc.dingtalk.service.CredentialsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 获取凭证接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 21:00
 */
@Slf4j
@RestController
@RequestMapping("/dingtalk")
public class CredentialsController {

    @Resource
    private CredentialsService credentialsService;

    @PostMapping("/access_token")
    public String getAccessToken(@RequestBody AppInfo appInfo) {
        return credentialsService.getAccessToken(appInfo);
    }

    @PostMapping(path = "/jsapi_ticket")
    public String getJsApiTicketByAccessToken(@RequestBody AppInfo appInfo) {
        return credentialsService.getJsApiTicket(appInfo);
    }

}
