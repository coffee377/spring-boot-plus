package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiGettokenRequest;
import com.voc.dingtalk.annotation.DingTalk;
import com.voc.dingtalk.annotation.PrimaryApp;
import com.voc.dingtalk.autoconfigure.App;
import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.service.IAppService;
import com.voc.dingtalk.url.Corp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 11:04
 */
@Slf4j
@DingTalk
@Service("dingtalkAppService")
public class AppService implements IAppService {

    private final DingTalkService dingTalkService;

    public AppService(DingTalkService dingTalkService) {
        this.dingTalkService = dingTalkService;
    }

    @Override
    public App getPrimaryApp() {
        return dingTalkService.getApps().stream()
                .filter(App::isPrimary).findFirst()
                .orElseThrow(() -> new DingTalkApiException(-1, "不存在的钉钉主应用"));
    }

    @Override
    public App getByIdOrName(String idOrName) {
        return dingTalkService.getApps().stream()
                .filter(app -> app.getAppKey().equals(idOrName) || app.getName().equals(idOrName))
                .findFirst()
                .orElseThrow(() -> new DingTalkApiException(-1, "不存在的钉钉应用"));
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.DING_TALK_APP_ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getAccessToken(String appNameOrAppId) {
        App app = getByIdOrName(appNameOrAppId);
        return getAppAccessToken(app.getAppKey(), app.getAppSecret());
    }

    @Override
    @PrimaryApp
    @Cacheable(cacheNames = DingTalkCache.DING_TALK_APP_ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getPrimaryAccessToken() {
        App app = getPrimaryApp();
        return getAppAccessToken(app.getAppKey(), app.getAppSecret());
    }

    /**
     * 获取应用访问令牌
     *
     * @param key    应用ID
     * @param secret 应用密钥
     * @return 访问令牌
     */
    private String getAppAccessToken(String key, String secret) {
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(key);
        request.setAppsecret(secret);
        request.setHttpMethod("GET");

        AtomicReference<String> accessToken = new AtomicReference<>();
        execute(Corp.GET_TOKEN, request, response -> accessToken.set(response.getAccessToken()));
        return accessToken.get();
    }

}
