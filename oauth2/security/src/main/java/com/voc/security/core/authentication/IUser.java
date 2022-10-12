package com.voc.security.core.authentication;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 08:53
 */
public interface IUser<ID extends Serializable> extends Serializable {

    /**
     * 用户唯一标识
     *
     * @return 用户标识
     */
    @NonNull
    ID getId();

    /**
     * 认证用户的用户名
     *
     * @return the username (never <code>null</code>)
     */
    @NonNull
    String getUsername();

    /**
     * 认证用户的密码
     *
     * @return the password
     */
    @Nullable
    String getPassword();

    /**
     * 用户权限标识
     *
     * @return Set<String>
     */
    Set<String> getAuthorities();

    /**
     * 设置用户权限标识
     *
     * @param authorities Set<String>
     */
    void setAuthorities(Set<String> authorities);

    /**
     * 指示用户的账户是否已过期。已过期的账号不允许身份验证
     *
     * @return boolean
     */
    boolean isAccountExpired();

    /**
     * 指示用户的账号是否被锁定。锁定的账号不允许身份验证
     *
     * @return boolean
     */
    boolean isAccountLocked();

    /**
     * 指示用户的凭据(密码)是否已过期。过期的凭据不允许身份验证
     *
     * @return boolean
     */
    boolean isCredentialsExpired();

    /**
     * 指示启用或禁用用户。被禁用的用户不能进行身份验证
     *
     * @return boolean
     */
    boolean isEnabled();


}
