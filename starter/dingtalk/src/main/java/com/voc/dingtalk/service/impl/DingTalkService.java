package com.voc.dingtalk.service.impl;

import com.voc.dingtalk.autoconfigure.App;
import com.voc.dingtalk.autoconfigure.DingTalkProperties;
import com.voc.dingtalk.cache.CacheInitListener;
import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.service.IDingTalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 11:12
 */
@Slf4j
@Service("dingTalkService")
public class DingTalkService implements IDingTalkService {

    private final DingTalkProperties dingTalkProperties;

    public DingTalkService(DingTalkProperties dingTalkProperties) {
        this.dingTalkProperties = dingTalkProperties;
    }

    @Override
    public String getCorpId() {
        return dingTalkProperties.getCorpId();
    }

    /**
     * 缓存所有钉钉应用配置信息
     * <p>在应用启动完成后清除 DingTalkCache.APP_INFO 缓存 {@link CacheInitListener}</p>
     *
     * @return List<App>
     */
    @Override
    @Cacheable(cacheNames = DingTalkCache.DING_TALK_APP, key = "'all'")
    public List<App> getApps() {
        List<App> apps = Optional.ofNullable(dingTalkProperties.getApp())
                .orElseGet(HashMap::new).entrySet()
                .stream().map(entry -> {
                    String name = entry.getKey();
                    App app = entry.getValue();
                    if (!StringUtils.hasText(app.getName())) {
                        app.setName(name);
                    }
                    return app;
                }).collect(Collectors.toList());
        log.debug("所有 APP 配置信息：{}", apps);
        return apps;
    }

}
