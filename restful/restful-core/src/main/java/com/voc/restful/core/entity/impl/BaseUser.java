package com.voc.restful.core.entity.impl;

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

    /**
     * 账户是否过期
     */
    private boolean accountExpired;

    /**
     * 账户是否锁定
     */
    private boolean accountLocked;

    /**
     * 密码是否过期
     */
    private boolean credentialsExpired;

    /**
     * 是否禁用
     */
    private boolean disabled;

    public BaseUser(ID id, String username, String password, boolean accountExpired, boolean accountLocked,
                    boolean credentialsExpired, boolean disabled) {
        this.setId(id);
        this.username = username;
        this.password = password;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.disabled = disabled;
    }

    public BaseUser(ID id, String username, String password) {
        this(id, username, password, false, false, false, false);
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
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return !disabled;
    }
}
