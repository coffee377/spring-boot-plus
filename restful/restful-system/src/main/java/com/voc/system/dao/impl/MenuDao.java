package com.voc.system.dao.impl;

import com.voc.restful.core.persist.mongo.impl.BaseMongoDao;
import com.voc.system.dao.IMenuDao;
import com.voc.system.entity.Menu;
import org.springframework.stereotype.Repository;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 14:49
 */
@Repository
public class MenuDao extends BaseMongoDao<Menu, String> implements IMenuDao {
}
