package com.voc.security.token;


import org.springframework.security.core.AuthenticationException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/18 20:57
 */
public class TokenConcurrencyException extends AuthenticationException {
    public TokenConcurrencyException(String msg) {
        super(msg);
    }
}
