package com.voc.security.core.service.impl;

import com.voc.security.core.authentication.IUser;
import com.voc.security.core.props.RootAccountProperties;
import com.voc.security.core.service.AuthService;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import java.util.Collection;
import java.util.Collections;

/**
 * 系统根用户
 *
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 01:43
 */
public class RootAccountService implements AuthService<String>, PriorityOrdered {
    public static String ROOT_AUTHORITY = "ROOT";
    private final RootAccountProperties account;

    public RootAccountService(RootAccountProperties account) {
        this.account = account;
    }

    @Override
    public IUser<String> getUserByUsername(String username) {
        if (!username.equals(account.getUsername())) return null;
        return new IUser<String>() {
            @Override
            public String getId() {
                return "0";
            }

            @Override
            public String getUsername() {
                return account.getUsername();
            }

            @Override
            public String getPassword() {
                return account.getPassword();
            }

            @Override
            public Collection<String> getAuthorities() {
                return Collections.singleton(ROOT_AUTHORITY);
            }
        };
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
