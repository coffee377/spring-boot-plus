package com.voc.dingtalk.service.impl;

import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetAccessTokenResponse;
import com.aliyun.tea.TeaException;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.voc.dingtalk.annotation.DingTalk;
import com.voc.dingtalk.annotation.PrimaryApp;
import com.voc.dingtalk.autoconfigure.model.App;
import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.service.AppService;
import com.voc.dingtalk.service.DingTalkService;
import com.voc.dingtalk.url.Corp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 11:04
 */
@Slf4j
@DingTalk
@Service("dingtalkAppService")
public class DefaultAppService implements AppService {

    private final DingTalkService dingTalkService;

    @Resource
    private Client oauth2Client;

    public DefaultAppService(DingTalkService dingTalkService) {
        this.dingTalkService = dingTalkService;
    }

    @Override
    public App getPrimaryApp() {
        return dingTalkService.getApps().stream()
                .filter(App::isPrimary).findFirst()
                .orElseThrow(() -> new DingTalkApiException("-1", "不存在的钉钉主应用"));
    }

    @Override
    public App getByIdOrName(String idOrName) {
        return dingTalkService.getApps().stream()
                .filter(app -> app.getAppKey().equals(idOrName) || app.getName().equals(idOrName))
                .findFirst()
                .orElseThrow(() -> new DingTalkApiException("-1", "不存在的钉钉应用"));
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.APP_ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getAccessToken(String appNameOrAppId) {
        App app = getByIdOrName(appNameOrAppId);
        return getAccessToken(app.getAppKey(), app.getAppSecret());
    }

    @Override
    @PrimaryApp
    @Cacheable(cacheNames = DingTalkCache.APP_ACCESS_TOKEN, keyGenerator = "appKeyGenerator")
    public String getPrimaryAccessToken() {
        App app = getPrimaryApp();
        return getAccessToken(app.getAppKey(), app.getAppSecret());
    }

    /**
     * 获取应用访问令牌（旧版）
     *
     * @param key    应用ID
     * @param secret 应用密钥
     * @return 访问令牌
     * @deprecated user
     */
    @Deprecated
    protected String getOldAppAccessToken(String key, String secret) {
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(key);
        request.setAppsecret(secret);
        request.setHttpMethod("GET");

        AtomicReference<String> accessToken = new AtomicReference<>();
        execute(Corp.GET_TOKEN, request, response -> accessToken.set(response.getAccessToken()));
        return accessToken.get();
    }

    /**
     * 获取企业内部应用的 accessToken
     *
     * @param appKey    已创建的企业内部应用的AppKey
     * @param appSecret 已创建的企业内部应用的AppSecret
     * @return 生成的 accessToken
     */
    protected String getAccessToken(String appKey, String appSecret) {
        GetAccessTokenRequest request = new GetAccessTokenRequest();
        request.setAppKey(appKey).setAppSecret(appSecret);
        GetAccessTokenResponse response;
        try {
            response = oauth2Client.getAccessToken(request);
            return response.getBody().getAccessToken();
        } catch (Exception e) {
            if (e instanceof TeaException) {
                TeaException teaException = (TeaException) e;
                throw new DingTalkApiException(teaException.code, teaException.message);
            } else {
                throw new DingTalkApiException(e.getMessage());
            }
        }
    }

}
