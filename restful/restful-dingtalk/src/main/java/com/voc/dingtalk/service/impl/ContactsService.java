package com.voc.dingtalk.service.impl;

import com.dingtalk.api.request.OapiV2DepartmentListsubRequest;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.request.OapiV2UserListRequest;
import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserListResponse;
import com.voc.dingtalk.service.IAppService;
import com.voc.dingtalk.service.IContactsService;
import com.voc.dingtalk.url.Contacts;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 15:25
 */
@Service
public class ContactsService implements IContactsService {
    private final IAppService appService;

    public ContactsService(IAppService appService) {
        this.appService = appService;
    }

    @Override
    public List<OapiV2DepartmentListsubResponse.DeptBaseResponse> getSubDepartment(Long departmentId) {
        OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
        req.setDeptId(departmentId);
        req.setLanguage("zh_CN");

        AtomicReference<List<OapiV2DepartmentListsubResponse.DeptBaseResponse>> department = new AtomicReference<>();
        execute(Contacts.V2_DEPARTMENT_LIST_SUB, req, appService.getPrimaryAccessToken(),
                response -> department.set(response.getResult()));
        return department.get();
    }

    @Override
    public List<OapiV2UserListResponse.ListUserResponse> getDepartmentUserDetails(Long departmentId) {
        OapiV2UserListRequest req = new OapiV2UserListRequest();
        req.setDeptId(departmentId);
        req.setCursor(0L);
        req.setSize(100L);
        req.setLanguage("zh_CN");
        req.setOrderField("entry_asc"); // 代表按照进入部门的时间升序

        AtomicReference<List<OapiV2UserListResponse.ListUserResponse>> department = new AtomicReference<>();
        pageRequest(req, department, appService.getPrimaryAccessToken());
        return department.get();
    }

    private void pageRequest(OapiV2UserListRequest req,
                             AtomicReference<List<OapiV2UserListResponse.ListUserResponse>> department, String accessToken) {
        execute(Contacts.V2_DEPARTMENT_USER_LIST, req, accessToken, response -> {
            OapiV2UserListResponse.PageResult pageResult = response.getResult();
            if (pageResult.getHasMore()) {
                req.setCursor(pageResult.getNextCursor());
                pageRequest(req, department, accessToken);
            }
            List<OapiV2UserListResponse.ListUserResponse> result = department.get();
            if (result != null) {
                result.addAll(pageResult.getList());
            } else {
                department.set(pageResult.getList());
            }
        });
    }

    @Override
    public OapiV2UserGetResponse.UserGetResponse getUserDetails(String userId) {
        OapiV2UserGetRequest req = new OapiV2UserGetRequest();
        req.setUserid(userId);
        req.setLanguage("zh_CN");

        AtomicReference<OapiV2UserGetResponse.UserGetResponse> user = new AtomicReference<>();
        execute(Contacts.V2_USER_DETAILS, req, appService.getPrimaryAccessToken(), res -> user.set(res.getResult()));
        return user.get();
    }
}
