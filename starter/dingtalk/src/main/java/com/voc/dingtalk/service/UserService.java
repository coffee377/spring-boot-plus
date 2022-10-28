package com.voc.dingtalk.service;

import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:40
 */
public interface UserService extends IApiExecutor {

    /**
     * 根据临时授权码获取用户开放信息
     *
     * @param accessKey    应用唯一标识 key
     * @param accessSecret 应用密钥
     * @param tmpAuthCode  临时授权码
     * @return OapiSnsGetuserinfoBycodeResponse.UserInfo
     * @see <a href="https://developers.dingtalk.com/document/app/obtain-the-user-information-based-on-the-sns-temporary-authorization</a>
     */
    OapiSnsGetuserinfoBycodeResponse.UserInfo getUserOpenInfoByCode(String accessKey, String accessSecret, String tmpAuthCode);

    /**
     * 根据应用唯一标识和临时授权码获取用户开放信息
     * @param clientId 应用唯一标识 key
     * @param tmpAuthCode 临时授权码
     * @return OapiSnsGetuserinfoBycodeResponse.UserInfo
     */
    OapiSnsGetuserinfoBycodeResponse.UserInfo getUserOpenInfoByClientIdAndTmpAuthCode(String clientId, String tmpAuthCode);

    /**
     * 通过临时授权码获取用户信息
     *
     * @param appName      应用名称
     * @param tempAuthCode 临时授权码
     * @return OapiSnsGetuserinfoBycodeResponse.UserInfo
     */
    OapiSnsGetuserinfoBycodeResponse.UserInfo getUserOpenInfoByCode(String appName, String tempAuthCode);

    /**
     * 获取用户在当前开发者企业账号范围内的唯一标识
     *
     * @param accessKey    应用Key
     * @param accessSecret 应用密钥
     * @param tmpAuthCode  临时授权码
     * @return String
     */
    @Deprecated
    String getUnionid(String accessKey, String accessSecret, String tmpAuthCode);

    /**
     * 获取用户在当前开发者企业账号范围内的唯一标识
     *
     * @param appName     应用名称
     * @param tmpAuthCode 临时授权码
     * @return String
     */
    @Deprecated
    String getUnionid(String appName, String tmpAuthCode);

    /**
     * 根据 unionid 获取 userid
     *
     * @param accessToken 应用访问令牌
     * @param unionid     用户在当前开发者企业账号范围内的唯一标识。
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
    OapiV2UserGetResponse.UserGetResponse getUserDetailInfo(String accessToken, String userid, String language);

    /**
     * 获取用户详细信息
     *
     * @param appName      应用名称
     * @param tempAuthCode 临时授权码
     * @return Object
     */
    Object getUserDetailInfo(String appName, String tempAuthCode);

    /**
     * 获取用户详细信息
     *
     * @param accessKey    应用Key
     * @param accessSecret 应用密钥
     * @param tempAuthCode 临时授权码
     * @return String
     */
    Object getUserDetailInfos(String accessKey, String accessSecret, String tempAuthCode);

    /**
     * 获取用户个人 token
     * @return
     */
//    Object getUserToken();

}
