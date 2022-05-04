package com.voc.system.service;

import com.voc.persist.mybatis.IBaseService;
import com.voc.system.dao.UserDao;
import com.voc.system.entity.bo.UserBO;
import com.voc.system.entity.po.UserPO;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/06 16:50
 */
public interface IUserService extends IBaseService<String, UserDao, UserPO, UserBO> {

    /**
     * 密码转换
     *
     * @param password  原始密码
     * @param plaintext 是否明文
     * @return 加密处理后
     */
    default String passwordConverter(String password, boolean plaintext) {
        return password;
    }

    /**
     * 添加用户
     *
     * @param entity        UserBO
     * @param plainPassword 是否存储约定格式明文密码(主要方便测试)
     * @return boolean
     */
    boolean save(UserBO entity, boolean plainPassword);
}
