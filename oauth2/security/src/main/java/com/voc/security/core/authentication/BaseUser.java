package com.voc.security.core.authentication;

import com.voc.common.api.dict.enums.UsingStatus;
import com.voc.common.api.entity.impl.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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
     * 密码(加密后的密码串)
     *
     * @see PasswordEncoderFactories#createDelegatingPasswordEncoder
     */
    private String password;

    /**
     * 权限
     */
    private Collection<String> authorities;

    /**
     * 账号是否过期
     */
    private boolean accountExpired;

    /**
     * 密码是否过期
     */
    private boolean credentialsExpired;

    public BaseUser(ID id, String username, String password, Collection<String> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = new HashSet<>(authorities);
    }

    public BaseUser(ID id, String username, String password, String... authorities) {
        this(id, username, password, Arrays.asList(authorities));
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
    public Collection<String> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountExpired() {
        return accountExpired;
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
        return credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        if (getStatus() != null) {
            return UsingStatus.NORMAL.equals(getStatus());
        }
        return true;
    }
}
