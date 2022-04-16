//package com.voc.system.service.impl;
//
//import com.voc.restful.core.persist.mongo.impl.BaseMongoService;
//import com.voc.restful.core.third.ThirdApp;
//import com.voc.system.dao.ISocialDao;
//import com.voc.system.dao.impl.UserDao;
//import com.voc.system.entity.po.UserBindPO;
//import com.voc.system.entity.po.UserPO;
//import com.voc.system.service.IUserService;
//import com.voc.system.entity.vo.user.UserVO;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import javax.annotation.Resource;
//import java.util.Collections;
//import java.util.Optional;
//import java.util.Set;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/07/06 16:52
// */
//public class UserService extends BaseMongoService<UserPO, UserDao> implements IUserService {
//
////    @Resource
////    private PasswordEncoder passwordEncoder;
//
//    @Resource
//    private ISocialDao socialDao;
//
//    @Override
//    public UserPO convertPassword(UserPO user, boolean plaintext) {
//        String password = user.getPassword();
////        if (plaintext) {
////            password = "{noop}" + password;
////        } else {
////            password = passwordEncoder.encode(user.getPassword());
////        }
////        user.setPassword(password);
//
//        return user;
//    }
//
//    @Override
//    public String save(UserPO entity, boolean plaintext) {
//        UserPO user = this.convertPassword(entity, plaintext);
//        return super.save(user);
//    }
//
//    @Override
//    public String save(UserPO entity) {
//        return this.save(entity, false);
//    }
//
//    /**
//     * @param username 用户名/邮箱/手机号/工号
//     * @return User
//     */
//    @Override
//    public UserPO getUserByUsername(String username) {
//        Criteria criteria = new Criteria().orOperator(
//                Criteria.where("username").is(username),
//                Criteria.where("email").is(username),
//                Criteria.where("mobile").is(username),
//                Criteria.where("jobNumber").is(username)
//        );
//        Query query = Query.query(criteria);
//        return this.findOne(query).orElse(null);
//    }
//
//    @Override
//    public UserPO getUserByThirdApp(ThirdApp app) {
//        Criteria criteria = new Criteria().orOperator(
//                Criteria.where("type").is(app.getType().get()),
//                Criteria.where("unionid").is(app.getUnionid()),
//                Criteria.where("openid").is(app.getOpenid())
//        );
//        Query query = Query.query(criteria);
//        Optional<UserBindPO> social = socialDao.findOne(query);
//        if (social.isPresent()) {
//            String uid = social.get().getUid();
//            return this.findById(uid);
//        }
//        return null;
//    }
//
//    @Override
//    public Set<String> getAuthorities(String uid) {
//        // TODO: 2021/7/7 16:33 获取用户权限
//        return Collections.emptySet();
//    }
//
//    @Override
//    public UserVO getUserInfo() {
//        return null;
//    }
//}
