package com.voc.dingtalk.service.impl;

import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.properties.App;
import com.voc.dingtalk.properties.DingTalkProperties;
import com.voc.dingtalk.service.IDingTalkService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 11:12
 */
@Service("dingTalkService")
public class DingTalkService implements IDingTalkService {

    @Resource
    private DingTalkProperties dingTalkProperties;

    @Resource
    private IDingTalkService dingTalkService;

    @Override
    public String getCorpId() {
        return dingTalkProperties.getCorpId();
    }

    @Override
    public List<App> getApps() {
        return dingTalkProperties.getApp().entrySet().stream().map(entry -> {
            String name = entry.getKey();
            App app = entry.getValue();
            if (!StringUtils.hasText(app.getName())) {
                app.setName(name);
            }
            return app;
        }).collect(Collectors.toList());
    }

    @Override
    public App getAppByName(String appName) {
        return dingTalkService.getApps().stream()
                .filter(app -> app.getName().equals(appName))
                .findFirst().orElse(new App());
    }

    @Override
    public App getAppById(String appId) {
        return dingTalkService.getApps().stream()
                .filter(app -> app.getAppKey().equals(appId))
                .findFirst().orElse(new App());
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.APP_SECRET, keyGenerator = "appKeyGenerator")
    public String getAppSecretByAppId(String appId) {
        return Optional.ofNullable(dingTalkService.getAppById(appId).getAppSecret()).orElse("");
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.APP_SECRET, keyGenerator = "appKeyGenerator")
    public String getAppSecretByAppName(String appName) {
        return Optional.ofNullable(dingTalkService.getAppByName(appName).getAppSecret()).orElse("");
    }


}
