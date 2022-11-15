package com.voc.security.oauth2.client;

import com.voc.security.oauth2.entity.po.OAuth2Client;
import com.voc.security.oauth2.service.OAuth2ClientService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.Assert;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/12 23:17
 */
public class CacheRegisteredClientRepository implements RegisteredClientRepository {
    public static final String OAUTH2_CLIENT_CACHE_NAME = "oauth2:client";

    private final OAuth2ClientService clientService;

    public CacheRegisteredClientRepository(OAuth2ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        insertOrUpdate(registeredClient);
    }

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#registeredClient.id"),
                    @CacheEvict(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#registeredClient.clientId")
            },
            put = {
                    @CachePut(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#registeredClient.clientId")
            }

    )
    protected OAuth2Client insertOrUpdate(RegisteredClient registeredClient) {
        Assert.notNull(registeredClient, "registeredClient cannot be null");
        OAuth2Client client = OAuth2Client.builder()
                .id(registeredClient.getId())
                .clientId(registeredClient.getClientId())
                .clientIdIssuedAt(registeredClient.getClientIdIssuedAt() == null ? Instant.now() : registeredClient.getClientIdIssuedAt())
                .clientSecret(registeredClient.getClientSecret())
                .clientSecretExpiresAt(registeredClient.getClientSecretExpiresAt())
                .clientName(registeredClient.getClientName())
                .clientAuthenticationMethods(null)
                .authorizationGrantTypes(null)
                .redirectUris(registeredClient.getRedirectUris())
                .scopes(registeredClient.getScopes())
//                .clientSettings("")
//                .tokenSettings("")
                .build();

        OAuth2Client existingRegisteredClient = clientService.findById(registeredClient.getId());
        if (existingRegisteredClient != null) {
//            clientService.updateById(client);
        } else {
//            clientService.add(client);
        }
        return existingRegisteredClient;
    }

    @Override
    @Cacheable(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#id", unless = "#result == null")
    public RegisteredClient findById(String id) {
        Assert.hasText(id, "id cannot be empty");
        OAuth2Client client = clientService.findById(id);
        return client.toRegisteredClient();
    }

    @Override
    @Cacheable(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#clientId", unless = "#result == null")
    public RegisteredClient findByClientId(String clientId) {
        Assert.hasText(clientId, "clientId cannot be empty");
        OAuth2Client client = clientService.findByClientId(clientId);
        return client.toRegisteredClient();
    }
}
