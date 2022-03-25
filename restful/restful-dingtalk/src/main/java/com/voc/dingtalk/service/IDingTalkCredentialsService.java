package com.voc.dingtalk.service;

import com.voc.dingtalk.exception.DingTalkApiException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 获取凭证服务
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:33
 */
public interface IDingTalkCredentialsService extends IApiExecutor {

    /**
     * 获取企业应用凭证
     *
     * @param appKey    应用唯一标识 key
     * @param appSecret 应用的密钥（可为空）
     * @return String
     * @throws DingTalkApiException API 异常
     */
    String getAccessToken(@NonNull String appKey, @Nullable String appSecret) throws DingTalkApiException;

    /**
     * 获取企业应用凭证
     *
     * @param appName 应用名称
     * @return String
     * @throws DingTalkApiException API 异常
     */
    String getAccessTokenByAppName(String appName) throws DingTalkApiException;

    /**
     * 获取jsapi ticket, 用于js的签名计算
     *
     * @param appKey    应用唯一标识 key
     * @param appSecret 应用的密钥
     * @return String
     * @throws DingTalkApiException API 异常
     */
    String getJsApiTicketByAppName(@NonNull String appKey, @Nullable String appSecret) throws DingTalkApiException;

    /**
     * 获取jsapi ticket, 用于js的签名计算
     *
     * @param appName 应用名称
     * @return String
     * @throws DingTalkApiException API 异常
     */
    String getJsApiTicketByAppName(String appName) throws DingTalkApiException;

}
