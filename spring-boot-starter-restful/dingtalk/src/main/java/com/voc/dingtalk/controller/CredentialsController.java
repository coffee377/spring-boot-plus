package com.voc.dingtalk.controller;

import com.voc.dingtalk.service.ICredentialsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 获取凭证接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 21:00
 */
@Slf4j
@RestController("dingTalkCredentialsController")
@RequestMapping("/dingtalk")
public class CredentialsController {

    @Resource
    private ICredentialsService credentialsService;

    @GetMapping("/{appName}/access_token")
    public String getAccessToken(@PathVariable("appName") String appName) {
        return credentialsService.getAccessToken(appName);
    }

    @GetMapping("/{appName}/jsapi_ticket")
    public String getJsApiTicketByAccessToken(@PathVariable("appName") String appName) {
        return credentialsService.getJsApiTicket(appName);
    }

}
