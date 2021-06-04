package com.voc.demo.service;

import com.voc.demo.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/01 16:16
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(value = "user", key = "#id", unless = "#result eq null")
    public User findById(String id) {
        User user = new User();
        user.setName("Wu Yujie");
        user.setAge(32);
        user.setSex("男");
        return user;
    }

    @Override
    @Cacheable("user")
    public List<User> findList(int nums) {
        List<User> users = new LinkedList<>();
        for (int i = 0; i < nums; i++) {
            User user = new User();
            user.setName(String.valueOf(i + 1));
            user.setAge(i + 10);
            user.setSex("男");
            users.add(user);
        }
        return new ArrayList<>(users);
    }

    @Override
    public void success() {

    }
}
