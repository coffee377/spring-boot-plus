package com.voc.restful.security.core.authentication.qrcode;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/23 16:34
 */
@Getter
public class QRAuthenticationToken extends PreAuthenticatedAuthenticationToken {
    private String type;
    private String clientId;
    private String code;


    public QRAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public QRAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials, null);
        setAuthenticated(false);
    }

    public QRAuthenticationToken(String type, String clientId, String code) {
        super(clientId, code, null);
        this.type = type;
        this.clientId = clientId;
        this.code = code;
        setAuthenticated(false);
    }
}
