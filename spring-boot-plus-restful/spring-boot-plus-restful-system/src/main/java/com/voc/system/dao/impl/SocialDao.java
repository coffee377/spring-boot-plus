package com.voc.system.dao.impl;

import com.voc.restful.core.persist.mongo.impl.BaseMongoDao;
import com.voc.system.dao.ISocialDao;
import com.voc.system.entity.Social;
import org.springframework.stereotype.Repository;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/08 14:10
 */
@Repository
public class SocialDao extends BaseMongoDao<Social, String> implements ISocialDao {
}
