package com.voc.system.dao.impl;

import com.voc.restful.core.persist.mongo.impl.BaseMongoDao;
import com.voc.system.dao.IUserDao;
import com.voc.system.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/07 11:29
 */
@Repository
public class UserDao extends BaseMongoDao<User, String> implements IUserDao {
}
