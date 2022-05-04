package com.voc.persist;

import com.voc.restful.core.entity.IUser;
import com.voc.restful.core.entity.impl.BaseUser;

import java.util.Optional;

/**
 * 系统默认账户
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/04 17:23
 */
public class DefaultAccountInfo implements IAccountInfo<String, IUser<String>> {
    @Override
    public String getUserId() {
        if (getUserInfo().isPresent()) {
            return getUserInfo().get().getId();
        }
        return null;
    }

    @Override
    public String getUserName() {
        if (getUserInfo().isPresent()) {
            return getUserInfo().get().getUsername();
        }
        return null;
    }

    @Override
    public Optional<IUser<String>> getUserInfo() {
        BaseUser<String> admin = new BaseUser<>("1", "admin", "123456");
        return Optional.of(admin);
    }
}
