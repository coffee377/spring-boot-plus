package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetbyunionidRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.voc.dingtalk.UrlConst;
import com.voc.dingtalk.service.ICredentialsService;
import com.voc.dingtalk.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:51
 */
@Service("dingtalkUserService")
public class UserService extends DingTalkService implements IUserService {

    @Resource
    private IUserService userService;

    @Resource
    private ICredentialsService credentialsService;

    @Override
    public OapiSnsGetuserinfoBycodeResponse.UserInfo getUserInfoByCode(String accessKey, String accessSecret, String tmpAuthCode) {
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(tmpAuthCode);
        AtomicReference<OapiSnsGetuserinfoBycodeResponse.UserInfo> userInfo = new AtomicReference<>();
        this.execute(UrlConst.SNS_GET_USER_INFO_BY_CODE, req, accessKey, accessSecret,
                response -> userInfo.set(response.getUserInfo()));
        return userInfo.get();
    }

    @Override
    public OapiSnsGetuserinfoBycodeResponse.UserInfo getUserInfoByCode(String appName, String tempAuthCode) {
        String accessToken = credentialsService.getAccessToken(appName);
        OapiSnsGetuserinfoBycodeRequest request = new OapiSnsGetuserinfoBycodeRequest();
        request.setTmpAuthCode(tempAuthCode);

        AtomicReference<OapiSnsGetuserinfoBycodeResponse.UserInfo> userInfo = new AtomicReference<>();
        this.execute(UrlConst.SNS_GET_USER_INFO_BY_CODE, request, accessToken,
                response -> userInfo.set(response.getUserInfo()));

        return userInfo.get();
    }

    @Override
    public String getUnionid(String accessKey, String accessSecret, String tmpAuthCode) {
        OapiSnsGetuserinfoBycodeResponse.UserInfo userInfo = this.getUserInfoByCode(accessKey, accessSecret, tmpAuthCode);
        return userInfo.getUnionid();
    }

    @Override
    public String getUnionid(String accessToken, String tmpAuthCode) {
        OapiSnsGetuserinfoBycodeResponse.UserInfo userInfo = this.getUserInfoByCode(accessToken, tmpAuthCode);
        return userInfo.getUnionid();
    }

    @Override
    public String getUserIdByUnionId(String accessToken, String unionId) {
        OapiUserGetbyunionidRequest request = new OapiUserGetbyunionidRequest();
        request.setUnionid(unionId);
        AtomicReference<String> uid = new AtomicReference<>();
        this.execute(UrlConst.TOP_API_USER_GET_BY_UNION_ID, request, accessToken,
                response -> uid.set(response.getResult().getUserid()));

        return uid.get();
    }

    @Override
    public OapiV2UserGetResponse.UserGetResponse getUserInfo(String accessToken, String userid, String language) {
        OapiV2UserGetRequest reqGetRequest = new OapiV2UserGetRequest();
        reqGetRequest.setUserid(userid);
        if (StringUtils.hasText(language)) {
            reqGetRequest.setLanguage("zh_CN");
        }
        AtomicReference<OapiV2UserGetResponse.UserGetResponse> reference = new AtomicReference<>();
        this.execute(UrlConst.TOP_API_V2_USER_GET, reqGetRequest, accessToken, response -> reference.set(response.getResult()));

        return reference.get();
    }

}
