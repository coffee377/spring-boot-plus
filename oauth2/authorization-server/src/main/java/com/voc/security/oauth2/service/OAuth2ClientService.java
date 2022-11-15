package com.voc.security.oauth2.service;

import com.voc.security.oauth2.entity.po.OAuth2Client;
import com.voc.security.oauth2.entity.dto.OAuth2ClientDTO;
import com.voc.security.oauth2.entity.vo.OAuth2ClientVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/12 00:16
 */
public interface OAuth2ClientService {
    String OAUTH2_CLIENT_CACHE_NAME = "oauth2:client";

    /**
     * 保存客户端信息
     *
     * @param client the client
     */
    OAuth2Client add(OAuth2ClientDTO client);

    /**
     * 更新客户端信息
     *
     * @param client the client
     */
    OAuth2Client update(OAuth2ClientDTO client);

    /**
     * 分页查询客户端信息
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<OAuth2ClientVO> page(Pageable pageable);

    /**
     * 根据 id 查找客户端
     *
     * @param id the id
     * @return the oauth2 client
     */
    OAuth2Client findById(String id);

    /**
     * 根据 clientId 查找客户端
     *
     * @param clientId 客户端 ID
     * @return OAuth2Client
     */
    OAuth2Client findByClientId(String clientId);

    /**
     * Remove by client id.
     *
     * @param id the id
     */
    void removeById(String id);

//
//    /**
//     * 获取认证提供商提供的用户信息
//     *
//     * @return OAuth2User
//     */
//    OAuth2User getUser(OAuthData data);
//
//    /**
//     * 获取账号
//     *
//     * @param data 认证数据
//     * @return 统一认证中心账号信息
//     */
//    Optional<OAuth2Account> getAccount(OAuth2User user);
//
//    /**
//     * 创建账号
//     *
//     * @return 账号 ID
//     */
//    OAuth2Account createAccount(OAuth2User user);
//
//    void bind(OAuth2Account account, OAuth2User user);
//
//
////    void unbind(AuthProvider provider);
//
//    /**
//     * @param data oauth2 认证数据
//     * @return 账号信息
//     */
//    default OAuth2Account login(OAuthData data) {
//        OAuth2User user = getUser(data);
//        Optional<OAuth2Account> account = getAccount(user);
//        return account.orElseGet(() -> {
//            OAuth2Account account11 = createAccount(user);
//            bind(account11, user);
//            return account11;
//        });
//    }
}
