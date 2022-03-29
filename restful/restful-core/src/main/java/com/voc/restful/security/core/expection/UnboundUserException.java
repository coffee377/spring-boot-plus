package com.voc.restful.security.core.expection;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/24 13:58
 */
public class UnboundUserException extends UsernameNotFoundException {
    public UnboundUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public UnboundUserException(String msg) {
        super(msg);
    }
}
