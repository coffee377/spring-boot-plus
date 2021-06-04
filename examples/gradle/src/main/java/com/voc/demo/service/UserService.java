package com.voc.demo.service;

import com.voc.demo.entity.User;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 15:40
 */
public interface UserService {
    /**
     * 查找用户
     * @param id 用户ID
     * @return User
     */
    User findById(String id);

    List<User> findList(int nums);

    /**
     * 无数据返回
     */
    void success();
}
