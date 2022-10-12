package com.voc.security.core.authentication;

import com.voc.security.core.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/01/15 19:59
 */
@Slf4j
public class DefaultUserDetailService implements UserDetailsService {

    private final AuthService authService;

    public DefaultUserDetailService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IUser<?> user = authService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<SimpleGrantedAuthority> authorities = user.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new DefaultUserDetails(user, authorities);
    }

}
