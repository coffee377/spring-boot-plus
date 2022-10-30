package com.voc.dingtalk.controller;

import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserListResponse;
import com.voc.dingtalk.autoconfigure.model.App;
import com.voc.dingtalk.service.AppService;
import com.voc.dingtalk.service.ContactsService;
import com.voc.dingtalk.service.DingTalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/19 21:00
 */
@Slf4j
@RestController("dingTalkController")
@RequestMapping("/dingtalk")
public class DingTalkController {

    @Resource
    private DingTalkService dingTalkService;

    @Resource
    private AppService appService;

    @Resource
    private ContactsService contactsService;

    /**
     * 查询应用列表
     *
     * @return List<App>
     */
    @GetMapping("/app")
    public List<App> getApps() {
        return dingTalkService.getApps();
    }

    /**
     * 获取应用访问令牌
     *
     * @param appNameOrAppId 应用ID或应用名称
     * @return 应用访问令牌
     */
    @GetMapping("/app/access_token")
    public String getAccessToken(@RequestParam(value = "app") String appNameOrAppId) {
        return appService.getAccessToken(appNameOrAppId);
    }

    /**
     * 获取主应用访问令牌
     *
     * @return 应用访问令牌
     */
    @GetMapping("/app/primary/access_token")
    public String getPrimaryAppAccessToken() {
        return appService.getPrimaryAccessToken();
    }

    /**
     * 显示子部门信息
     *
     * @param depId 钉钉父部门ID
     * @return List<OapiV2DepartmentListsubResponse.DeptBaseResponse>
     */
    @GetMapping("/department")
    public List<OapiV2DepartmentListsubResponse.DeptBaseResponse> querySubDepartment(@RequestParam(name = "id", required = false) Long depId) {
        return contactsService.getSubDepartment(depId);
    }

    /**
     * 显示部门成员信息
     *
     * @param depId 钉钉部门ID
     * @return List<OapiV2UserListResponse.ListUserResponse>
     */
    @GetMapping("/department/{id}/user")
    public List<OapiV2UserListResponse.ListUserResponse> queryDepartmentUser(@PathVariable(name = "id") Long depId) {
        return contactsService.getDepartmentUserDetails(depId);
    }

    /**
     * 获取用户详情
     *
     * @param userId 钉钉用户ID
     * @return OapiV2UserGetResponse.UserGetResponse
     */
    @GetMapping("/department/user/{id}")
    public OapiV2UserGetResponse.UserGetResponse queryUserDetails(@PathVariable(name = "id") String userId) {
        return contactsService.getUserDetails(userId);
    }
}
