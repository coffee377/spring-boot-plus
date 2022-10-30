package com.voc.security.oauth2;

import lombok.Builder;
import lombok.Data;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/30 00:04
 */
@Data
@Builder(builderClassName = "Builder")
public class DingTalkOAuth2TokenResponse {

    private String accessToken;

    private String refreshToken;

    private Long expireIn;

    private String corpId;
}
