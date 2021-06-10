package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.voc.dingtalk.UrlConst;
import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.properties.App;
import com.voc.dingtalk.service.ICredentialsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:43
 */
@Slf4j
@Service("dingTalkCredentialsService")
public class CredentialsService extends DingTalkService implements ICredentialsService, DingTalkCache {

    @Resource
    private ICredentialsService credentialsService;

    @Override
    @Cacheable(cacheNames = DingTalkCache.ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getAccessToken(String appKey, String appSecret) throws DingTalkApiException {
        Assert.hasText(appKey, "appKey can be empty");
        OapiGettokenRequest request = new OapiGettokenRequest();

        request.setAppkey(appKey);
        if (StringUtils.hasText(appSecret)) {
            request.setAppsecret(appSecret);
        } else {
            String secret = credentialsService.getAppSecretByAppId(appKey);
            request.setAppsecret(secret);
        }
        request.setHttpMethod("GET");

        AtomicReference<String> accessToken = new AtomicReference<>();
        this.execute(UrlConst.GET_TOKEN, request, response -> accessToken.set(response.getAccessToken()));
        return accessToken.get();
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getAccessToken(String appName) throws DingTalkApiException {
        App app = credentialsService.getAppByName(appName);
        return credentialsService.getAccessToken(app.getAppKey(), app.getAppSecret());
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.JS_TICKET, keyGenerator = "appKeyGenerator")
    public String getJsApiTicket(String appKey, String appSecret) throws DingTalkApiException {
        String accessToken = credentialsService.getAccessToken(appKey, appSecret);
        OapiGetJsapiTicketRequest request = new OapiGetJsapiTicketRequest();
        request.setTopHttpMethod("GET");

        AtomicReference<String> ticket = new AtomicReference<>();
        this.execute(UrlConst.GET_JSAPI_TICKET, request, accessToken, response -> ticket.set(response.getTicket()));
        return ticket.get();
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.JS_TICKET, keyGenerator = "appKeyGenerator")
    public String getJsApiTicket(String appName) throws DingTalkApiException {
        App app = credentialsService.getAppByName(appName);
        return credentialsService.getJsApiTicket(app.getAppKey(), app.getAppSecret());
    }
}
