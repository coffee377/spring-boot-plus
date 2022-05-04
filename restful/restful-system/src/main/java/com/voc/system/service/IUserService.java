package com.voc.system.service;

import com.voc.restful.core.persist.entity.BizEntity;
import com.voc.restful.core.persist.mybatis.IBaseService;
import com.voc.system.dao.UserDao;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/06 16:50
 */
public interface IUserService extends IBaseService<String, UserDao, BizEntity> {

}
