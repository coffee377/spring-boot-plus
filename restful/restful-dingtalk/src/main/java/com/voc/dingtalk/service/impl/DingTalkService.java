package com.voc.dingtalk.service.impl;

import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.properties.DingTalkApp;
import com.voc.dingtalk.properties.DingTalkProperties;
import com.voc.dingtalk.service.IDingTalkService;
import com.voc.restful.core.response.impl.InternalBizStatus;
import com.voc.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 11:12
 */
@Slf4j
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

    /**
     * TODO: 2021/6/11 9:43 在应用启动完成后清除 DingTalkCache.APP_INFO 缓存
     *
     * @return List<App>
     */
    @Override
    @Cacheable(cacheNames = DingTalkCache.APP_INFO, key = "'apps'")
    public List<DingTalkApp> getApps() {
        return dingTalkProperties.getApp().entrySet().stream().map(entry -> {
            String name = entry.getKey();
            DingTalkApp app = entry.getValue();
            if (!StringUtils.hasText(app.getName())) {
                app.setName(name);
            }
            return app;
        }).collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.APP_INFO)
    public DingTalkApp getAppByName(String appName) {
        return dingTalkService.getApps().stream()
                .filter(app -> app.getName().equals(appName))
                .findFirst()
                .orElseThrow(() -> {
                    BizException exception = new BizException(InternalBizStatus.RECORD_NOT_EXISTS.message("相关应用配置信息不存在"));
                    log.error("appName = {} 的配置信息不存在", appName, exception);
                    return exception;
                });
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.APP_INFO)
    public DingTalkApp getAppById(String appId) {
        return dingTalkService.getApps().stream()
                .filter(app -> app.getAppKey().equals(appId))
                .findFirst()
                .orElseThrow(() -> {
                    BizException exception = new BizException(InternalBizStatus.RECORD_NOT_EXISTS.message("相关应用配置信息不存在"));
                    log.error("appKey = {} 的配置信息不存在", appId, exception);
                    return exception;
                });
    }

    @Override
    public String ensureAppSecret(String appKey, String appSecret) {
        Assert.hasText(appKey, "appKey must not be empty");
        if (StringUtils.hasText(appSecret)) {
            return appSecret;
        } else {
            return dingTalkService.getAppById(appKey).getAppSecret();
        }
    }


}
