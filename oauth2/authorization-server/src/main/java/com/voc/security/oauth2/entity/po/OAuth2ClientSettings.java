package com.voc.security.oauth2.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.util.StringUtils;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 01:08
 */
@Data
@Accessors(chain = true)
public class OAuth2ClientSettings {
    private boolean requireProofKey; // false
    private boolean requireAuthorizationConsent; // false
    private String jwkSetUrl; //

    /**
     * token 端点认证签名算法
     */
    private String tokenEndpointAuthenticationSigningAlgorithm; //

    public ClientSettings toClientSettings() {
        ClientSettings.Builder builder = ClientSettings.builder();
        builder.requireProofKey(requireProofKey).requireAuthorizationConsent(requireAuthorizationConsent);
        if (StringUtils.hasText(jwkSetUrl)) {
            builder.jwkSetUrl(jwkSetUrl);
        }
        JwsAlgorithm jwsAlgorithm = null;
        if (StringUtils.hasText(tokenEndpointAuthenticationSigningAlgorithm)) {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.from(tokenEndpointAuthenticationSigningAlgorithm);
            if (signatureAlgorithm != null) {
                jwsAlgorithm = signatureAlgorithm;
            }
            MacAlgorithm macAlgorithm = MacAlgorithm.from(tokenEndpointAuthenticationSigningAlgorithm);
            if (macAlgorithm != null) {
                jwsAlgorithm = macAlgorithm;
            }

            if (jwsAlgorithm != null) {
                builder.tokenEndpointAuthenticationSigningAlgorithm(jwsAlgorithm);
            }
        }
        return builder.build();
    }

    public static OAuth2ClientSettings from(ClientSettings settings) {
        OAuth2ClientSettings clientSettings = new OAuth2ClientSettings();
        clientSettings.setRequireProofKey(settings.isRequireProofKey())
                .setRequireAuthorizationConsent(settings.isRequireAuthorizationConsent())
                .setJwkSetUrl(settings.getJwkSetUrl());
        return clientSettings;
    }

}
