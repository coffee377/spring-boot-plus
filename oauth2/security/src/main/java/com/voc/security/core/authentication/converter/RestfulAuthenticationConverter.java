package com.voc.security.core.authentication.converter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/05 12:25
 */
public class RestfulAuthenticationConverter implements AuthenticationConverter {
    @Override
    public UsernamePasswordAuthenticationToken convert(HttpServletRequest request) {
        return null;
    }
}
