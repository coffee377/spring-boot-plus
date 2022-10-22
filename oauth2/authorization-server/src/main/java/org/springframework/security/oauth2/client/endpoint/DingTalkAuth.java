package org.springframework.security.oauth2.client.endpoint;

import lombok.Builder;
import lombok.Data;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/03 22:42
 */
@Data
@Builder
public class DingTalkAuth {

    private String clientId;

    private String clientSecret;

    private String code;

    private String refreshToken;

    private String grantType;
}
