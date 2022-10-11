package com.voc.oauth2.api;

import com.voc.oauth2.api.model.Account;
import com.voc.oauth2.api.model.OAuthData;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/10 10:35
 */
public interface OAuth2Service {

    /**
     * 获取认证提供商提供的用户信息
     *
     * @return OAuth2User
     */
    OAuth2User getUser(OAuthData data);

    /**
     * 获取账号
     *
     * @param data 认证数据
     * @return 统一认证中心账号信息
     */
    Optional<Account> getAccount(OAuth2User user);

    /**
     * 创建账号
     *
     * @return 账号 ID
     */
    Account createAccount(OAuth2User user);

    void bind(Account account, OAuth2User user);


//    void unbind(AuthProvider provider);

    /**
     * @param data oauth2 认证数据
     * @return 账号信息
     */
    default Account login(OAuthData data) {
        OAuth2User user = getUser(data);
        Optional<Account> account = getAccount(user);
        return account.orElseGet(() -> {
            Account account11 = createAccount(user);
            bind(account11, user);
            return account11;
        });
    }
}
