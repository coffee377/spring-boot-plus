package com.voc.system.dao;

import com.voc.restful.core.persist.mongo.IMongoDao;
import com.voc.system.entity.Role;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/15 17:20
 */
@NoRepositoryBean
public interface IRoleDao extends IMongoDao<Role, String> {
}
