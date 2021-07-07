package com.voc.system.service.impl;

import com.voc.restful.core.persist.mongo.impl.BaseMongoService;
import com.voc.restful.core.third.ThirdApp;
import com.voc.system.dao.impl.UserDao;
import com.voc.system.entity.impl.User;
import com.voc.system.service.IUserService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/06 16:52
 */
public class UserService extends BaseMongoService<User, UserDao> implements IUserService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public String save(User entity) {
        String encode = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encode);
        return super.save(entity);
    }

    /**
     * @param username 用户名/邮箱/手机号
     * @return User
     */
    @Override
    public User getUserByUsername(String username) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("username").is(username),
                Criteria.where("mobile").is(username),
                Criteria.where("email").is(username)
        );
        Query query = Query.query(criteria);
        return this.findOne(query).orElse(null);
    }

    @Override
    public User getUserByThirdApp(ThirdApp app) {
        // TODO: 2021/7/7 16:34 获取绑定用户信息
        return null;
    }

    @Override
    public Set<String> getAuthorities(String uid) {
        // TODO: 2021/7/7 16:33 获取用户权限
        return Collections.emptySet();
    }

}
