package com.voc.restful.security.token.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/16 09:42
 */
@Data
@ConfigurationProperties(prefix = "api.security.token.jwt")
public class JwtProperties {

    /**
     * jwt 签名算法
     */
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;

    /**
     * jwt issuer, 默认 https://tvoc.site
     *
     */
    private String issuer = "https://tvoc.site";


}
