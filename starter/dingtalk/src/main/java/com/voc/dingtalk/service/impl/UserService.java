package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetbyunionidRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.voc.common.api.biz.BizException;
import com.voc.common.api.biz.InternalBizStatus;
import com.voc.dingtalk.autoconfigure.App;
import com.voc.dingtalk.exception.DingTalkApiException;
import com.voc.dingtalk.service.IAppService;
import com.voc.dingtalk.service.IUserService;
import com.voc.dingtalk.url.Corp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:51
 */
@Slf4j
@Service("dingtalkUserService")
public class UserService implements IUserService {

    @Resource
    private IAppService appService;

    @Override
    public OapiSnsGetuserinfoBycodeResponse.UserInfo getUserOpenInfoByCode(String accessKey, String accessSecret,
                                                                           String tmpAuthCode) {
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(tmpAuthCode);
        AtomicReference<OapiSnsGetuserinfoBycodeResponse.UserInfo> userInfo = new AtomicReference<>();
        this.execute(Corp.SNS_GET_USER_INFO_BY_CODE, req, accessKey, accessSecret,
                response -> userInfo.set(response.getUserInfo()));
        return userInfo.get();
    }

    @Override
    public OapiSnsGetuserinfoBycodeResponse.UserInfo getUserOpenInfoByClientIdAndTmpAuthCode(String clientId, String tmpAuthCode) {
        App app = appService.getByIdOrName(clientId);
        String appKey = app.getAppKey();
        String appSecret = app.getAppSecret();
        return this.getUserOpenInfoByCode(appKey, appSecret, tmpAuthCode);
    }

    @Override
    public OapiSnsGetuserinfoBycodeResponse.UserInfo getUserOpenInfoByCode(String appName, String tempAuthCode) {
        App app = appService.getByIdOrName(appName);
        String appKey = app.getAppKey();
        String appSecret = app.getAppSecret();
        return this.getUserOpenInfoByCode(appKey, appSecret, tempAuthCode);
    }

    @Override
    public String getUnionid(String accessKey, String accessSecret, String tmpAuthCode) {
        OapiSnsGetuserinfoBycodeResponse.UserInfo userInfo = this.getUserOpenInfoByCode(accessKey, accessSecret, tmpAuthCode);
        return userInfo.getUnionid();
    }

    @Override
    public String getUnionid(String appName, String tmpAuthCode) {
        OapiSnsGetuserinfoBycodeResponse.UserInfo userInfo = this.getUserOpenInfoByCode(appName, tmpAuthCode);
        return userInfo.getUnionid();
    }

    @Override
    public String getUserIdByUnionId(String accessToken, String unionId) {
        OapiUserGetbyunionidRequest request = new OapiUserGetbyunionidRequest();
        request.setUnionid(unionId);
        AtomicReference<String> uid = new AtomicReference<>();
        this.execute(Corp.TOP_API_USER_GET_BY_UNION_ID, request, accessToken,
                response -> uid.set(response.getResult().getUserid()));

        return uid.get();
    }

    @Override
    public OapiV2UserGetResponse.UserGetResponse getUserDetailInfo(String accessToken, String userid, String language) {
        OapiV2UserGetRequest reqGetRequest = new OapiV2UserGetRequest();
        reqGetRequest.setUserid(userid);
        if (!StringUtils.hasText(language)) {
            reqGetRequest.setLanguage("zh_CN");
        }
        AtomicReference<OapiV2UserGetResponse.UserGetResponse> reference = new AtomicReference<>();
        this.execute(Corp.TOP_API_V2_USER_GET, reqGetRequest, accessToken,
                response -> reference.set(response.getResult()));

        return reference.get();
    }

    @Override
    public Map<String, Object> getUserDetailInfo(String appName, String tempAuthCode) {
        String accessToken = appService.getAccessToken(appName);
        String unionid = this.getUnionid(appName, tempAuthCode);
        String uid = this.getUserIdByUnionId(accessToken, unionid);
        OapiV2UserGetResponse.UserGetResponse userInfo = this.getUserDetailInfo(accessToken, uid, "");
//        IJson json = SpringUtils.getBean(IJson.class);
//        String serializer = json.serializer(userInfo);
//        return (Map<String, Object>) json.deserializer(serializer, Map.class);
        throw new BizException(InternalBizStatus.UN_IMPLEMENTED_METHOD);
    }

    @Override
    public Object getUserDetailInfos(String accessKey, String accessSecret, String tempAuthCode) {
        String accessToken = appService.getAccessToken(accessKey);
        String unionid = this.getUnionid(accessKey, accessSecret, tempAuthCode);
        String uid = this.getUserIdByUnionId(accessToken, unionid);
        OapiV2UserGetResponse.UserGetResponse userInfo = this.getUserDetailInfo(accessToken, uid, "");
        return userInfo;
    }
}
