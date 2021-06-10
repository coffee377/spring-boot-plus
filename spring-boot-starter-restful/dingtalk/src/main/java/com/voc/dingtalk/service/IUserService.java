package com.voc.dingtalk.service;

import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:40
 */
public interface IUserService extends IDingTalkService {

    /**
     * 根据临时授权码获取用户信息
     *
     * @param accessKey    应用唯一标识 key
     * @param accessSecret 应用密钥
     * @param tmpAuthCode  临时授权码
     * @return OapiSnsGetuserinfoBycodeResponse.UserInfo
     * @see <a href="https://developers.dingtalk.com/document/app/obtain-the-user-information-based-on-the-sns-temporary-authorization</a>
     */
    OapiSnsGetuserinfoBycodeResponse.UserInfo getUserInfoByCode(String accessKey, String accessSecret, String tmpAuthCode);

    /**
     * 通过临时授权码获取用户信息
     *
     * @param appName  应用名称
     * @param tempAuthCode 临时授权码
     * @return OapiSnsGetuserinfoBycodeResponse.UserInfo
     */
    OapiSnsGetuserinfoBycodeResponse.UserInfo getUserInfoByCode(String appName, String tempAuthCode);

    /**
     * 获取用在当前开放应用所属企业的唯一标识
     *
     * @param accessKey    应用Key
     * @param accessSecret 应用密钥
     * @param tmpAuthCode  临时授权码
     * @return String
     */
    String getUnionid(String accessKey, String accessSecret, String tmpAuthCode);

    /**
     * 获取用在当前开放应用所属企业的唯一标识
     *
     * @param accessToken 应用访问令牌
     * @param tmpAuthCode 临时授权码
     * @return String
     */
    String getUnionid(String accessToken, String tmpAuthCode);

    /**
     * 根据 unionid 获取 userid
     *
     * @param accessToken 应用访问令牌
     * @param unionid     用户在当前开放应用所属企业的唯一标识
     * @return String 用户ID
     */
    String getUserIdByUnionId(String accessToken, String unionid);

    /**
     * 根据 userid 获取用户所在企业详细信息
     *
     * @param accessToken 应用访问令牌
     * @param userid      用户ID
     * @param language    语言
     * @return String
     */
    OapiV2UserGetResponse.UserGetResponse getUserInfo(String accessToken, String userid, String language);


}
