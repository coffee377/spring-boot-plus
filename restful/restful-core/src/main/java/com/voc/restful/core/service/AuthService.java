package com.voc.restful.core.service;

import com.voc.restful.core.entity.IUser;
import com.voc.restful.core.third.ThirdApp;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 14:44
 */
public interface AuthService<ID extends Serializable> {
    String BEAN_NAME = "com.voc.restful.core.service.AuthService";

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名/邮箱/手机号
     * @return IUser<ID>
     */
    IUser<ID> getUserByUsername(String username);

    /**
     * 获取与第三方应用绑定的用户
     *
     * @param app 第三方应用信息
     * @return IUser<ID>
     */
    IUser<ID> getUserByThirdApp(ThirdApp app);

    /**
     * 根据唯一标识获取用户拥有的权限信息（包含角色信息等）
     *
     * @param uid 用户唯一标识
     * @return 权限集合
     */
    Set<String> getAuthorities(ID uid);

}
