package com.voc.dingtalk.service;

import com.voc.dingtalk.properties.App;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 11:10
 */
public interface IDingTalkService extends IApiExecutor {

    /**
     * 获取企业ID
     *
     * @return String
     */
    String getCorpId();

    /**
     * 获取所有配置的应用
     *
     * @return App
     */
    List<App> getApps();

    /**
     * 根据名称获取应用配置信息
     *
     * @param appName 应用名称
     * @return App
     */
    App getAppByName(String appName);

    /**
     * 根据应用ID获取应用配置信息
     *
     * @param appId 应用唯一标识 key
     * @return App
     */
    App getAppById(String appId);

    /**
     * 返回应用密钥
     *
     * @param appKey    应用唯一标识 key
     * @param appSecret 应用密钥
     * @return String
     */
    String ensureAppSecret(@NonNull String appKey, @Nullable String appSecret);

}
