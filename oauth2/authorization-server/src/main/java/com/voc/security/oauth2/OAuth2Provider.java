package com.voc.security.oauth2;

import com.voc.security.core.authentication.AuthProvider;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/12 23:35
 */
public interface OAuth2Provider extends AuthProvider {

    /**
     * 用户在第三方平台唯一 ID
     *
     * @return
     */
    String getUnionId();

    /**
     * 用户在第三方平台某个开放应用 ID
     *
     * @return
     */
    String getOpenId();
}
