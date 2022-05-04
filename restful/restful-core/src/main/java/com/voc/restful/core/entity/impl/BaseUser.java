package com.voc.restful.core.entity.impl;

import com.voc.api.enums.UsingStatus;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.IUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/20 11:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseUser<ID extends Serializable> extends BaseEntity<ID> implements IUser<ID> {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public BaseUser(ID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public BaseUser() {
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountExpired() {
        return false;
    }

    @Override
    public boolean isAccountLocked() {
        if (getStatus() != null) {
            return UsingStatus.LOCK.equals(getStatus());
        }
        return false;
    }

    @Override
    public boolean isCredentialsExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        if (getStatus() != null) {
            return UsingStatus.NORMAL.equals(getStatus());
        }
        return true;
    }
}
