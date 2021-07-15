package com.voc.system.service;

import com.voc.restful.core.persist.mongo.IMongoService;
import com.voc.system.dao.impl.MenuDao;
import com.voc.system.entity.Menu;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 15:56
 */
public interface IMenuService extends IMongoService<Menu, MenuDao> {
}
