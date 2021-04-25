package com.voc.dingtalk.service;

import com.voc.dingtalk.entity.AppInfo;
import com.voc.dingtalk.exception.DingTalkApiException;

/**
 * 获取凭证服务
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:33
 */
public interface CredentialsService extends ApiExecutor {

    /**
     * 获取企业凭证
     *
     * @param appInfo 应用信息
     * @return String
     * @throws DingTalkApiException API 异常
     */
    String getAccessToken(AppInfo appInfo) throws DingTalkApiException;

    /**
     * 获取jsapi ticket, 用于js的签名计算
     *
     * @param appInfo 应用信息
     * @return String
     * @throws DingTalkApiException API 异常
     */
    String getJsApiTicket(AppInfo appInfo) throws DingTalkApiException;

}
