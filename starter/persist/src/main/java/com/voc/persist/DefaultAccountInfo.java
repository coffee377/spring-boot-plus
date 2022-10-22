package com.voc.persist;//package com.voc.persist;
//
//
//import java.util.Optional;
//
///**
// * 系统默认账户
// *
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2022/05/04 17:23
// */
//public class DefaultAccountInfo implements IAccountInfo<String, IUser<String>> {
//    @Override
//    public String getUserId() {
//        if (getUserInfo().isPresent()) {
//            return getUserInfo().get().getId();
//        }
//        return null;
//    }
//
//    @Override
//    public String getUserName() {
//        if (getUserInfo().isPresent()) {
//            return getUserInfo().get().getUsername();
//        }
//        return null;
//    }
//
//    @Override
//    public Optional<IUser<String>> getUserInfo() {
//        // TODO: 2022/7/12 8:49 从配置获取超级管理员用户
//        BaseUser<String> admin = new BaseUser<>("root", "admin", "123456");
////        admin.setAuthorities();
//        return Optional.of(admin);
//    }
//}
