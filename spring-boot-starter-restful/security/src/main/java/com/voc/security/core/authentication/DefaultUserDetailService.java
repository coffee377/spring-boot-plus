package com.voc.security.core.authentication;

import com.voc.restful.core.entity.IUser;
import com.voc.restful.core.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/01/15 19:59
 */
@Slf4j
public class DefaultUserDetailService implements UserDetailsService {

    private final AuthService<Serializable> authService;

    public DefaultUserDetailService(AuthService<Serializable> userService) {
        this.authService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IUser<Serializable> user = authService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<GrantedAuthority> authorities = this.getAuthorities(user.getId());
        return new DefaultUserDetails(user, authorities);
    }

    /**
     * 根据用户名获取用户角色信息
     *
     * @param uid 用户唯一标识
     * @return Collection<GrantedAuthority>
     */
    private Collection<GrantedAuthority> getAuthorities(Serializable uid) {
        Set<String> authorities = authService.getAuthorities(uid);
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

}
