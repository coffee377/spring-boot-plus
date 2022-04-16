//package com.voc.system.service;
//
//import com.voc.restful.core.persist.mongo.IMongoService;
//import com.voc.restful.core.service.AuthService;
//import com.voc.restful.core.third.ThirdApp;
//import com.voc.system.dao.impl.UserDao;
//import com.voc.system.entity.po.UserPO;
//import com.voc.system.entity.vo.user.UserVO;
//
//import java.util.Set;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/07/06 16:50
// */
//public interface IUserService extends AuthService<String>, IMongoService<UserPO, UserDao> {
//
//    /**
//     * 转换密码
//     *
//     * @param user      User
//     * @param plaintext 是否明文
//     * @return User
//     */
//    UserPO convertPassword(UserPO user, boolean plaintext);
//
//    /**
//     * 保存用户
//     *
//     * @param user      User
//     * @param plaintext boolean
//     * @return User
//     */
//    String save(UserPO user, boolean plaintext);
//
//    /**
//     * 根据用户名获取用户
//     *
//     * @param username 用户名/邮箱/手机号
//     * @return User
//     */
//    @Override
//    UserPO getUserByUsername(String username);
//
//    /**
//     * 获取与第三方应用关联的用户
//     *
//     * @param app 第三方应用信息
//     * @return IUser<ID>
//     */
//    @Override
//    UserPO getUserByThirdApp(ThirdApp app);
//
//    /**
//     * 根据用户名获取用户拥有的权限信息（包含角色信息等）
//     *
//     * @param uid 用户唯一标识
//     * @return 权限集合
//     */
//    @Override
//    Set<String> getAuthorities(String uid);
//
////        /**
////     * 获取用户拥有的菜单
////     *
////     * @return Collection<Menu>
////     */
////    Collection<Menu> getMenus();
//
//    /**
//     * 获取当前用户信息
//     *
//     * @return UserVO
//     */
//    UserVO getUserInfo();
//
////    /**
////     * 添加用户
////     *
////     * @param user 用户信息
////     * @return 用户唯一表示
////     */
////    String add(User user);
////
////    /**
////     * 批量添加用户
////     *
////     * @param users 用户列表
////     * @return 用户ID集合
////     */
////    String[] add(List<User> users);
////
////    /**
////     * 根据用户 ID 删除用户
////     *
////     * @param uid 用户ID
////     */
////    void deleteById(String uid);
////
////    /**
////     * 批量删除用户
////     *
////     * @param ids 用户 id 集合
////     */
////    void batchDelete(String... ids);
////
////    /**
////     * 更新用户信息
////     *
////     * @param user 用户信息
////     * @return 更新后的用户信息
////     */
////    User update(User user);
////
////    /**
////     * 添加角色
////     *
////     * @param roles 角色集合
////     */
////    void addRole(IRole... roles);
////
////    /**
////     * 添加角色
////     *
////     * @param roles 角色集合
////     */
////    void remove(IRole... roles);
//}
