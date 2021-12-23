package com.voc.system.dao;

import com.voc.restful.core.persist.mongo.IMongoDao;
import com.voc.system.entity.User;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/07 11:29
 */
@NoRepositoryBean
public interface IUserDao extends IMongoDao<User, String> {
}
