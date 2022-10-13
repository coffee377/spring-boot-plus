package com.voc.security.oauth2.service;

import com.voc.security.oauth2.OAuthData;
import com.voc.security.oauth2.entity.Account;
import com.voc.security.oauth2.entity.OAuth2Client;
import com.voc.security.oauth2.entity.dto.OAuth2ClientDTO;
import com.voc.security.oauth2.entity.vo.OAuth2ClientVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/12 00:16
 */
public interface OAuth2ClientService {


    OAuth2Client save(OAuth2ClientDTO client);

    /**
     * Save client.
     *
     * @param client the client
     */
//    void saveClient(OAuth2ClientDTO client);

    /**
     * Update.
     *
     * @param client the client
     */
    void update(OAuth2ClientDTO client);

    /**
     * Page page.
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<OAuth2ClientVO> page(Pageable pageable);

    /**
     * Find client by id o auth 2 client.
     *
     * @param id the id
     * @return the o auth 2 client
     */
    OAuth2Client  findClientById(String id);

    /**
     * Remove by client id.
     *
     * @param id the id
     */
    void removeByClientId(String id);


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
