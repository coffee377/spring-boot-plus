package com.voc.system.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.voc.persist.mybatis.impl.BaseService;
import com.voc.system.dao.UserDao;
import com.voc.system.entity.bo.UserBO;
import com.voc.system.entity.mapstruct.UserMapper;
import com.voc.system.entity.po.UserPO;
import com.voc.system.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/06 16:52
 */
@Service
public class UserService extends BaseService<String, UserDao, UserPO, UserBO> implements IUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserPO convert(UserBO userBO) {
        return UserMapper.INSTANCE.convertBO2PO(userBO);
    }

    @Override
    public String passwordConverter(String password, boolean plaintext) {
        if (plaintext){
            return "{noop}".concat(password);
        }
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean save(UserBO entity, boolean plainPassword) {
        UserPO persist = this.convert(entity);
        String password = entity.getPassword();
        String pwd = this.passwordConverter(password, plainPassword);
//        persist.setPassword(pwd);
        int result = this.getDao().insert(persist);
        return SqlHelper.retBool(result);
    }

    @Override
    public boolean save(UserBO entity) {
        return this.save(entity, false);
    }
}
