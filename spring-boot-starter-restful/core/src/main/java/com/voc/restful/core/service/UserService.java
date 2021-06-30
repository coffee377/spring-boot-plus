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
public interface UserService<ID extends Serializable> {
    String BEAN_NAME = "com.voc.restful.core.service.UserService";

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名/邮箱/手机号
     * @return IUser<ID>
     */
    IUser<ID> getUserByUsername(String username);

    /**
     * 获取与第三方应用关联的用户
     *
     * @param app 第三方应用信息
     * @return IUser<ID>
     */
    IUser<ID> getUserByThirdApp(ThirdApp app);

    /**
     * 根据用户名获取用户拥有的权限信息（包含角色信息等）
     *
     * @param uid 用户唯一标识
     * @return 权限集合
     */
    Set<String> getAuthorities(ID uid);

//    /**
//     * 获取用户拥有的菜单
//     *
//     * @return Collection<Menu>
//     */
//    Collection<Menu> getMenus();

//    /**
//     * 添加用户
//     *
//     * @param user 用户信息
//     * @return 用户唯一表示
//     */
//    String add(User user);
//
//    /**
//     * 批量添加用户
//     *
//     * @param users 用户列表
//     * @return 用户ID集合
//     */
//    String[] add(List<User> users);
//
//    /**
//     * 根据用户 ID 删除用户
//     *
//     * @param uid 用户ID
//     */
//    void deleteById(String uid);
//
//    /**
//     * 批量删除用户
//     *
//     * @param ids 用户 id 集合
//     */
//    void batchDelete(String... ids);
//
//    /**
//     * 更新用户信息
//     *
//     * @param user 用户信息
//     * @return 更新后的用户信息
//     */
//    User update(User user);
//
//    /**
//     * 添加角色
//     *
//     * @param roles 角色集合
//     */
//    void addRole(IRole... roles);
//
//    /**
//     * 添加角色
//     *
//     * @param roles 角色集合
//     */
//    void remove(IRole... roles);

}
