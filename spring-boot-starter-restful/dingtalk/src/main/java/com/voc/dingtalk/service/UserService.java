package com.voc.dingtalk.service;

import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:40
 */
public interface UserService extends ApiExecutor {

    /**
     * 通过临时授权码获取用户信息
     *
     * @param accessToken
     * @param tempAuthCode
     * @return String
     */
    String getUnionId(String accessToken, String tempAuthCode);

    /**
     * 根据 unionid 获取 userid
     *
     * @param accessToken
     * @param unionid
     * @return String
     */
    String getUserIdByUnionId(String accessToken, String unionid);

    /**
     * 根据 unionid 获取 userid
     *
     * @param accessToken
     * @param userid
     * @return String
     */
    Map<String, Object> getUserInfo(String accessToken, String userid);

}
