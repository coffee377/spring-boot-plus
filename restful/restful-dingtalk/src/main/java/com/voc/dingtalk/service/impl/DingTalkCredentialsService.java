package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.voc.dingtalk.UrlConst;
import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.properties.App;
import com.voc.dingtalk.service.IDingTalkCredentialsService;
import com.voc.dingtalk.service.IDingTalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:43
 */
@Slf4j
@Service("dingTalkCredentialsService")
public class DingTalkCredentialsService implements IDingTalkCredentialsService, DingTalkCache {

    @Resource
    private IDingTalkCredentialsService credentialsService;

    @Resource
    private IDingTalkService dingTalkService;

    @Override
    @Cacheable(cacheNames = DingTalkCache.ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getAccessToken(String appKey, String appSecret) throws DingTalkApiException {
        String secret = dingTalkService.ensureAppSecret(appKey, appSecret);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(secret);
        request.setHttpMethod("GET");

        AtomicReference<String> accessToken = new AtomicReference<>();
        this.execute(UrlConst.GET_TOKEN, request, response -> accessToken.set(response.getAccessToken()));
        return accessToken.get();
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getAccessTokenByAppName(String appName) throws DingTalkApiException {
        App app = dingTalkService.getAppByName(appName);
        return credentialsService.getAccessToken(app.getAppKey(), app.getAppSecret());
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.JS_TICKET, keyGenerator = "appKeyGenerator")
    public String getJsApiTicketByAppName(String appKey, String appSecret) throws DingTalkApiException {
        String secret = dingTalkService.ensureAppSecret(appKey, appSecret);
        String accessToken = credentialsService.getAccessToken(appKey, secret);
        OapiGetJsapiTicketRequest request = new OapiGetJsapiTicketRequest();
        request.setTopHttpMethod("GET");

        AtomicReference<String> ticket = new AtomicReference<>();
        this.execute(UrlConst.GET_JSAPI_TICKET, request, accessToken, response -> ticket.set(response.getTicket()));
        return ticket.get();
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.JS_TICKET, keyGenerator = "appKeyGenerator")
    public String getJsApiTicketByAppName(String appName) throws DingTalkApiException {
        App app = dingTalkService.getAppByName(appName);
        return credentialsService.getJsApiTicketByAppName(app.getAppKey(), app.getAppSecret());
    }
}
