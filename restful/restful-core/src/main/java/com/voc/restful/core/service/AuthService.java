package com.voc.restful.core.service;

import com.voc.restful.core.entity.IUser;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 14:44
 */
public interface AuthService<ID extends Serializable> {

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return IUser<ID>
     */
    IUser<ID> getUserByUsername(String username);

}
