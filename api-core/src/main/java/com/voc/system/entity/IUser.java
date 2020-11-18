package com.voc.system.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/11 15:41
 */
public interface IUser<PK> extends IGeneric<PK, PK>, UserDetails {

    /**
     * 设置用户名
     *
     * @param username String
     */
    void setUsername(String username);

    /**
     * 设置密码
     *
     * @param password String
     */
    void setPassword(String password);

    /**
     * 设置用户权限
     *
     * @param authorities A
     * @param <A>         A extends Collection<? extends GrantedAuthority>
     */
    <A extends Collection<? extends GrantedAuthority>> void setAuthorities(A authorities);

}
