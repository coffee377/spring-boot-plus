package com.voc.restful.core.third;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/25 11:25
 */
public interface ThirdAppService {

    /**
     * 获取第三方应用信息
     *
     * @return AppInfo
     */
    AppName getAppInfo();

    /**
     * 获取第三应用用户信息
     *
     * @param clientId 客户端 ID
     * @param code     临时授权码
     * @return ThirdApp
     * @throws AuthenticationException 身份验证异常
     */
    ThirdApp getUserInfoByClientIdAndTmpAuthCode(String clientId, String code) throws AuthenticationException;
}
