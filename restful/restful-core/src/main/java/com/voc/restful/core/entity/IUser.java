package com.voc.restful.core.entity;

import com.voc.persist.entity.IEntity;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 08:53
 */
public interface IUser<ID extends Serializable> extends IEntity<ID> {

    /**
     * 认证用户的用户名
     *
     * @return the username (never <code>null</code>)
     */
    String getUsername();

    /**
     * 认证用户的密码
     *
     * @return the password
     */
    String getPassword();

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
