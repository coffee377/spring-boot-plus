package com.voc.security.oauth2.service.impl;

import com.voc.security.oauth2.OAuthData;
import com.voc.security.oauth2.entity.Account;
import com.voc.security.oauth2.entity.OAuth2Client;
import com.voc.security.oauth2.entity.dto.OAuth2ClientDTO;
import com.voc.security.oauth2.entity.vo.OAuth2ClientVO;
import com.voc.security.oauth2.service.OAuth2ClientService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/12 23:17
 */
public class DefaultOAuth2ClientService implements OAuth2ClientService {
    @Override
    public OAuth2Client save(OAuth2ClientDTO client) {
        return null;
    }

    @Override
    public void update(OAuth2ClientDTO client) {

    }

    @Override
    public Page<OAuth2ClientVO> page(Pageable pageable) {
        return null;
    }

    @Override
    public OAuth2Client findClientById(String id) {
        return null;
    }

    @Override
    public void removeByClientId(String id) {

    }

    @Override
    public OAuth2User getUser(OAuthData data) {
        return null;
    }

    @Override
    public Optional<Account> getAccount(OAuth2User user) {
        return Optional.empty();
    }

    @Override
    public Account createAccount(OAuth2User user) {
        return null;
    }

    @Override
    public void bind(Account account, OAuth2User user) {

    }

    @Override
    public void save(RegisteredClient registeredClient) {
        OAuth2Client client = OAuth2Client.fromRegisteredClient(registeredClient);
    }

    @Override
    @Cacheable(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#id", unless = "#result == null")
    public RegisteredClient findById(String id) {
        Assert.hasText(id, "id cannot be empty");
//        OAuth2Client client = clientMapper.findById(id);
        return null;
    }

    @Override
    @Cacheable(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#clientId", unless = "#result == null")
    public RegisteredClient findByClientId(String clientId) {
        Assert.hasText(clientId, "clientId cannot be empty");
//        OAuth2Client client = clientMapper.findByClientId(clientId);
        return null;
    }
}
