//package com.voc.security.core.service.impl;
//
//
//import com.voc.security.core.authentication.BaseUser;
//import com.voc.security.core.authentication.IUser;
//import com.voc.security.core.service.AuthService;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/06/20 11:35
// */
//public class DefaultAuthService implements AuthService<String> {
//
//    private final Map<String, IUser<String>> users = new HashMap<>(3);
//
//    public DefaultAuthService() {
//        /* 初始用户 */
//        users.put("admin", new BaseUser<>("1", "admin", "{noop}123456", "ROLE_ADMIN", "ROLE_KICKING"));
//        users.put("demo", new BaseUser<>("2", "demo", "{noop}123456", "ROLE_DEMO", "SCOPE_DEMO"));
//        users.put("test", new BaseUser<>("3", "test", "{noop}123456", "ROLE_TEST", "SCOPE_TEST"));
//
//    }
//
//    @Override
//    public IUser<String> getUserByUsername(String username) {
//        return users.get(username);
//    }
//
//}
