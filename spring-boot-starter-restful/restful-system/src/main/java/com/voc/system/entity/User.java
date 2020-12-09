//package com.voc.system.entity;
//
//import com.voc.api.enums.DataFlag;
//import com.voc.api.enums.UsingStatus;
//import com.voc.system.entity.impl.BaseEntity;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2020/10/19 18:10
// */
//@Getter
//@Setter
//public class User extends BaseEntity<String, String> implements IUser<String> {
//
//    private String username;
//
//    private String password;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    @Override
//    public <A extends Collection<? extends GrantedAuthority>> void setAuthorities(A grantedAuthorities) {
//        this.authorities = grantedAuthorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UsingStatus.NORMAL.equals(getStatus());
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return !DataFlag.DELETE.equals(getFlag());
//    }
//}
