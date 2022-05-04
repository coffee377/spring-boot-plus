package com.voc.restful.security.service;

import com.voc.restful.core.entity.IUser;
import com.voc.restful.core.service.AuthService;
import com.voc.restful.core.third.ThirdApp;

import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/21 11:21
 */
public class AuthUserService implements AuthService<String> {

    private final AuthUserDao userDao;

    public AuthUserService(AuthUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public IUser<String> getUserByUsername(String username) {
//        UserPO userPO = userDao.selectOne(new QueryWrapper<UserPO>().eq("USERNAME", "coffee377"));
//        userDao.selectOne(new QueryWrapper<>());
        return null;
    }

    @Override
    public IUser<String> getUserByThirdApp(ThirdApp app) {
        return null;
    }

    @Override
    public Set<String> getAuthorities(String uid) {
        return null;
    }
}
