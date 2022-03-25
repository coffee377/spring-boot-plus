package com.voc.system.dao;

import com.voc.restful.core.persist.mongo.IMongoDao;
import com.voc.system.entity.Menu;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 15:12
 */
@NoRepositoryBean
public interface IMenuDao extends IMongoDao<Menu, String> {

}
