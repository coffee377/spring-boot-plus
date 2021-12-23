package com.voc.system.dao.impl;

import com.voc.restful.core.persist.mongo.impl.BaseMongoDao;
import com.voc.system.dao.IRoleDao;
import com.voc.system.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/15 17:22
 */
@Repository
public class RoleDao extends BaseMongoDao<Role, String> implements IRoleDao {
}
