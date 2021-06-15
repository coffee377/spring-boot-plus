package com.voc.security.authentication;

import com.voc.security.authority.RoleGrantedAuthority;
import com.voc.security.authority.ScopeGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/01/15 19:59
 */
@Slf4j
public class DefaultUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = this.getAuthorities(username);
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return "{noop}123456";
            }

            @Override
            public String getUsername() {
                return "admin";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    /**
     * 根据用户名获取用户角色信息
     *
     * @param username 用户名
     * @return Collection<GrantedAuthority>
     */
    private Collection<GrantedAuthority> getAuthorities(String username) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        AuthorityUtils.createAuthorityList("ROLE_ADMIN","SCOPE_messages.read");
        GrantedAuthority roleAdmin = new RoleGrantedAuthority("ROLE_ADMIN");
        GrantedAuthority roleAdmin2 = new ScopeGrantedAuthority("SCOPE_messages.read");
        authorities.add(roleAdmin);
        authorities.add(roleAdmin2);
        return authorities;
    }

}
