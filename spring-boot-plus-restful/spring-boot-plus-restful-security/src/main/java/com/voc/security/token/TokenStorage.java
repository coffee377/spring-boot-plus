package com.voc.security.token;

import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

import java.time.Instant;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/16 09:18
 */
public interface TokenStorage {

    /**
     * 生成访问令牌
     *
     * @param uid           系统用户唯一标识
     * @param tokenResponse OAuth2AccessTokenResponse
     * @return access_token
     */
    String createAccessToken(String uid, OAuth2AccessTokenResponse tokenResponse);

    /**
     * 生成刷新令牌
     *
     * @param uid           系统用户唯一标识
     * @param tokenResponse OAuth2AccessTokenResponse
     * @return refresh_token
     */
    String createRefreshToken(String uid, OAuth2AccessTokenResponse tokenResponse);

    /**
     * 在线用户统计
     *
     * @return List<OnlineUser>
     */
    List<OnlineUser> onlineStatistics();

    /**
     * 下线所有用户
     */
    void offlineAll();

    /**
     * 下线指定用户，用户的双令牌均失效
     *
     * @param uid 用户唯一标识
     */
    void offline(String uid);

    /**
     * 下线指定时间签发的用户
     *
     * @param uid      用户唯一标识
     * @param issuedAt 签发时间
     */
    void offline(String uid, Instant issuedAt);

}
