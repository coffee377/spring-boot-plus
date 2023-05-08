package com.voc.security.core.authentication;

import com.voc.security.core.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/01/15 19:59
 */
@Slf4j
public class DefaultUserDetailService implements UserDetailsService {

    private final List<AuthService> authServices;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserDetailService(ObjectProvider<AuthService> authServiceProvider, PasswordEncoder passwordEncoder) {
        /* 根据注入的 AuthService 按顺序进行排列 */
        this.authServices = authServiceProvider.orderedStream().collect(Collectors.toList());
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IUser<?> user = null;
        /* 1. 循环递归查找用户 */
        for (AuthService authService : authServices) {
            user = authService.getUserByUsername(username);
            if (user != null) {
                break;
            }
        }

        /* 2. 用户未找到找抛出异常 */
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        /* 3. 构建用户权限 */
        Set<SimpleGrantedAuthority> authorities = user.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return User.withUsername(user.getUsername())
                .password(Objects.requireNonNull(user.getPassword()))
                .accountExpired(user.isAccountExpired())
                .accountLocked(user.isAccountLocked())
                .authorities(authorities)
                .credentialsExpired(user.isCredentialsExpired())
                .disabled(!user.isEnabled())
                .build();
    }

}
