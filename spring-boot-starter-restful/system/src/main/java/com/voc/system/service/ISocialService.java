package com.voc.system.service;

import com.voc.restful.core.persist.mongo.IMongoService;
import com.voc.system.dao.impl.SocialDao;
import com.voc.system.entity.Social;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/08 14:43
 */
public interface ISocialService extends IMongoService<Social, SocialDao> {

    /**
     * 绑定系统用户
     *
     * @param social Social
     * @return String
     */
    String bindUser(Social social);

}
