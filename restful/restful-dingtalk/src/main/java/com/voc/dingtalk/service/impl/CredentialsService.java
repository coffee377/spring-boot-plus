package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.voc.dingtalk.autoconfigure.App;
import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.service.IAppService;
import com.voc.dingtalk.service.ICredentialsService;
import com.voc.dingtalk.url.Corp;
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
public class CredentialsService implements ICredentialsService, DingTalkCache {

    @Resource
    private ICredentialsService credentialsService;

    private final IAppService appService;

    public CredentialsService(IAppService appService) {
        this.appService = appService;
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.DING_TALK_APP_ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getAccessToken(String appKey, String appSecret) throws DingTalkApiException {
        String secret = appService.ensureAppSecret(appKey, appSecret);
        return this.getAppAccessToken(appKey, secret);
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.DING_TALK_APP_ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getAccessTokenByAppName(String appName) throws DingTalkApiException {
        App app = appService.getByIdOrName(appName);
        return this.getAppAccessToken(app.getAppKey(), app.getAppSecret());
    }

    private String getAppAccessToken(String key, String secret) {
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(key);
        request.setAppsecret(secret);
        request.setHttpMethod("GET");

        AtomicReference<String> accessToken = new AtomicReference<>();
        this.execute(Corp.GET_TOKEN, request, response -> accessToken.set(response.getAccessToken()));
        return accessToken.get();
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.DING_TALK_APP_JS_TICKET, keyGenerator = "appKeyGenerator")
    public String getJsApiTicketByAppName(String appKey, String appSecret) throws DingTalkApiException {
        String secret = appService.ensureAppSecret(appKey, appSecret);
        String accessToken = credentialsService.getAccessToken(appKey, secret);
        OapiGetJsapiTicketRequest request = new OapiGetJsapiTicketRequest();
        request.setTopHttpMethod("GET");

        AtomicReference<String> ticket = new AtomicReference<>();
        execute(Corp.GET_JSAPI_TICKET, request, accessToken, response -> ticket.set(response.getTicket()));
        return ticket.get();
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.DING_TALK_APP_JS_TICKET, keyGenerator = "appKeyGenerator")
    public String getJsApiTicketByAppName(String appName) throws DingTalkApiException {
        App app = appService.getByIdOrName(appName);
        return credentialsService.getJsApiTicketByAppName(app.getAppKey(), app.getAppSecret());
    }
}
