package com.voc.system.service.impl;

import com.voc.restful.core.persist.entity.BizEntity;
import com.voc.restful.core.persist.entity.PersistEntity;
import com.voc.restful.core.persist.entity.QueryEntity;
import com.voc.restful.core.persist.mybatis.impl.BaseService;
import com.voc.system.dao.UserDao;
import com.voc.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/06 16:52
 */
@Service
public class UserService extends BaseService<String, UserDao, PersistEntity, BizEntity, QueryEntity> implements IUserService {

}
