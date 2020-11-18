package com.voc.api.service;

import com.voc.system.entity.IUser;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/11 15:51
 */
public interface IUserService {

    /**
     * 根据用户名获取用户
     *
     * @param username String
     * @return IUser
     */
    IUser getUserByUsername(String username);

}
