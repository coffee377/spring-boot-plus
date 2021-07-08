package com.voc.system.dao;

import com.voc.restful.core.persist.mongo.IMongoDao;
import com.voc.system.entity.Social;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/08 14:09
 */
@NoRepositoryBean
public interface ISocialDao extends IMongoDao<Social, String> {
}
