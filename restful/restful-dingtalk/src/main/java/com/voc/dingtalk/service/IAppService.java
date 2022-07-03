package com.voc.dingtalk.service;

import com.voc.dingtalk.autoconfigure.App;
import com.voc.dingtalk.exception.DingTalkApiException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 钉钉应用服务
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 11:01
 */
public interface IAppService extends IApiExecutor {

    /**
     * 获取主应用信息
     *
     * @return App
     */
    App getPrimaryApp();

    /**
     * 根据应用ID或应用名称获取应用配置信息
     *
     * @param idOrName 应用ID或名称
     * @return App
     */
    App getByIdOrName(String idOrName);

    /**
     * 获取企业应用凭证
     *
     * @param appNameOrAppId 应用名称
     * @return String
     * @throws DingTalkApiException API 异常
     */
    String getAccessToken(String appNameOrAppId) throws DingTalkApiException;

    /**
     * 返回应用密钥
     *
     * @param appKey    应用唯一标识 key
     * @param appSecret 应用密钥
     * @return String
     */
    @Deprecated
    String ensureAppSecret(@NonNull String appKey, @Nullable String appSecret);

}
