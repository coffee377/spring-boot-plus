package com.voc.security.oauth2.service;

import com.voc.security.oauth2.entity.OAuth2Client;
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
}
