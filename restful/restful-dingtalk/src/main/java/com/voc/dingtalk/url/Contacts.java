package com.voc.dingtalk.url;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通讯录相关接口地址
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 10:40
 */
@Getter
@AllArgsConstructor
public enum Contacts implements UrlPath {
    /**
     * 获取下一级部门基础信息
     */
    V2_DEPARTMENT_LIST_SUB("/topapi/v2/department/listsub"),
    /**
     * 获取部门用户详情
     */
    V2_DEPARTMENT_USER_LIST("/topapi/v2/user/list"),
    /**
     * 查询用户详情
     */
    V2_USER_DETAILS("/topapi/v2/user/get"),
    ;

    private final String path;
}
