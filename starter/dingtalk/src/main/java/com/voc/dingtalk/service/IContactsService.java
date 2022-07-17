package com.voc.dingtalk.service;

import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserListResponse;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 通讯录服务
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 15:11
 */
public interface IContactsService extends IApiExecutor {

    /**
     * 获取下一级部门基础信息
     *
     * @param departmentId 父部门ID
     * @return List<OapiV2DepartmentListsubResponse.DeptBaseResponse>
     */
    List<OapiV2DepartmentListsubResponse.DeptBaseResponse> getSubDepartment(@Nullable Long departmentId);

    /**
     * 获取部门用户详情
     *
     * @param departmentId 部门ID
     * @return List<OapiV2UserListResponse.ListUserResponse>
     */
    List<OapiV2UserListResponse.ListUserResponse> getDepartmentUserDetails(Long departmentId);

    /**
     * 获取用户详情
     *
     * @param userId 用户ID
     * @return OapiV2UserGetResponse.UserGetResponse
     */
    OapiV2UserGetResponse.UserGetResponse getUserDetails(String userId);

    /**
     * 一次性同步钉钉数据
     *
     * @param clear 是否清空原有数据
     */
    void syncDataOnce(boolean clear);
}
