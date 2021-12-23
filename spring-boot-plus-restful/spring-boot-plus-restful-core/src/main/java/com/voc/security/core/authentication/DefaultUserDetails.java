package com.voc.security.core.authentication;

import com.voc.restful.core.entity.IUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/24 14:09
 */
public class DefaultUserDetails implements UserDetails {

    private final IUser user;
    private final Collection<? extends GrantedAuthority> authorities;

    public DefaultUserDetails(IUser user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public DefaultUserDetails(IUser user) {
        this(user, Collections.emptyList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
