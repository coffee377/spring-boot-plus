package com.voc.security.core.authentication.qrcode;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/12 15:16
 */
public class QRAuthenticationConverter implements AuthenticationConverter {
    @Override
    public AbstractAuthenticationToken convert(HttpServletRequest request) {
        // TODO: 2022/10/12 15:17
        QRAuthenticationToken qrAuthenticationToken = new QRAuthenticationToken("", "", "");
        return null;
    }
}
