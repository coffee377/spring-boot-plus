package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.voc.dingtalk.UrlConst;
import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.entity.AppInfo;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.service.CredentialsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:43
 */
@Slf4j
@Service("credentialsService")
public class CredentialsServiceImpl implements CredentialsService, DingTalkCache {

    @Override
    @Cacheable(
            cacheNames = DingTalkCache.ACCESS_TOKEN,
            key = "#appInfo.appKey",
            condition = "#appInfo.appKey != null and #appInfo.appSecret != null",
            unless = "#result == null"
    )
    public String getAccessToken(AppInfo appInfo) throws DingTalkApiException {
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appInfo.getAppKey());
        request.setAppsecret(appInfo.getAppSecret());
        request.setHttpMethod("GET");

        AtomicReference<String> accessToken = new AtomicReference<>();
        this.execute(UrlConst.ACCESS_TOKEN, request, response -> accessToken.set(response.getAccessToken()));
        return accessToken.get();
    }

    public String getJsTicket(String accessToken) throws DingTalkApiException {
        OapiGetJsapiTicketRequest request = new OapiGetJsapiTicketRequest();
        request.setTopHttpMethod("GET");

        AtomicReference<String> ticket = new AtomicReference<>();
        this.execute(UrlConst.JSAPI_TICKET, request, accessToken, response -> ticket.set(response.getTicket()));
        return ticket.get();
    }

    @Override
    @Cacheable(
            cacheNames = DingTalkCache.JS_TICKET,
            key = "#appInfo.appKey",
            condition = "#appInfo.appKey != null",
            unless = "#result == null"
    )
    public String getJsApiTicket(AppInfo appInfo) throws DingTalkApiException {
        String token = appInfo.getAccessToken();

        if (!StringUtils.hasLength(token)) {
            token = this.getAccessToken(appInfo);
        }

        return this.getJsTicket(token);
    }
}
