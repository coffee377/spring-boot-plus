package com.voc.restful.security.oauth2;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/12 23:17
 */
public class MybatisRegisteredClientRepository implements RegisteredClientRepository {
    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return null;
    }
}
