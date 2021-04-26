package com.voc.system.service;

import com.voc.system.entity.User;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 14:44
 */
public interface IUserService {

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 用户唯一表示
     */
    String add(User user);

    /**
     * 批量添加用户
     *
     * @param users 用户列表
     * @return 用户ID集合
     */
    String[] add(List<User> users);

    /**
     * 根据用户 ID 删除用户
     *
     * @param uid 用户ID
     */
    void deleteById(String uid);

    /**
     * 批量删除用户
     *
     * @param ids 用户 id 计划
     */
    void batchDelete(String... ids);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 更新后的用户信息
     */
    User update(User user);

}
