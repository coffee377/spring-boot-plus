package com.voc.security.oauth2.service.impl;

import com.voc.security.oauth2.entity.po.OAuth2Client;
import com.voc.security.oauth2.entity.dto.OAuth2ClientDTO;
import com.voc.security.oauth2.entity.vo.OAuth2ClientVO;
import com.voc.security.oauth2.service.OAuth2ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/12 23:17
 */
@Service
public class DefaultOAuth2ClientService implements OAuth2ClientService {
    @Override
    public OAuth2Client add(OAuth2ClientDTO client) {
        return null;
    }

    @Override
    public OAuth2Client update(OAuth2ClientDTO client) {
        return null;
    }

    @Override
    public Page<OAuth2ClientVO> page(Pageable pageable) {
        return null;
    }

    @Override
    public OAuth2Client findById(String id) {
        return null;
    }

    @Override
    public OAuth2Client findByClientId(String clientId) {
        return null;
    }

    @Override
    public void removeById(String id) {

    }

    //    @Override
//    @Cacheable(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#id", unless = "#result == null")
//    public RegisteredClient findById(String id) {
//        Assert.hasText(id, "id cannot be empty");
////        OAuth2Client client = clientMapper.findById(id);
//        return null;
//    }
//
//    @Override
//    @Cacheable(cacheNames = OAUTH2_CLIENT_CACHE_NAME, key = "#clientId", unless = "#result == null")
//    public RegisteredClient findByClientId(String clientId) {
//        Assert.hasText(clientId, "clientId cannot be empty");
////        OAuth2Client client = clientMapper.findByClientId(clientId);
//        return null;
//    }
}
