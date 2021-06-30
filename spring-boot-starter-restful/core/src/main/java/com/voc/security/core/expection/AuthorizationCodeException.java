package com.voc.security.core.expection;

import org.springframework.security.core.AuthenticationException;

/**
 * 临时授权码异常
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/24 12:16
 */
public class AuthorizationCodeException extends AuthenticationException {

    public AuthorizationCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthorizationCodeException(String msg) {
        super(msg);
    }

}
