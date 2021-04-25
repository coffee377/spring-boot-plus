package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetbyunionidRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.voc.dingtalk.UrlConst;
import com.voc.dingtalk.service.UserService;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/21 21:51
 */
public class UserServiceImpl implements UserService {

    @Override
    public String getUnionId(String accessToken, String tempAuthCode) {
        OapiSnsGetuserinfoBycodeRequest request = new OapiSnsGetuserinfoBycodeRequest();
        request.setTmpAuthCode(tempAuthCode);

        AtomicReference<String> uid = new AtomicReference<>();
        this.execute(UrlConst.SNS_GET_USER_INFO_BY_CODE, request, accessToken,
                response -> uid.set(response.getUserInfo().getUnionid()));

        return uid.get();
    }

    @Override
    public String getUserIdByUnionId(String accessToken, String unionId) {
        OapiUserGetbyunionidRequest request = new OapiUserGetbyunionidRequest();
        request.setUnionid(unionId);
        AtomicReference<String> uid = new AtomicReference<>();
        this.execute(UrlConst.TOP_API_USER_GET_BY_UNION_ID, request, "accessToken",
                response -> uid.set(response.getResult().getUserid()));

        return uid.get();
    }

    @Override
    public Map<String, Object> getUserInfo(String accessToken, String userid) {
        OapiV2UserGetRequest reqGetRequest = new OapiV2UserGetRequest();
        reqGetRequest.setUserid(userid);
        reqGetRequest.setLanguage("zh_CN");

        return null;
    }
}
