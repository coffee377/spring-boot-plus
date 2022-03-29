package com.voc.restful.security.authority;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/15 10:03
 */
public class ScopeGrantedAuthority extends PrefixGrantedAuthority {

    public ScopeGrantedAuthority(String name) {
        super("SCOPE", name);
    }

}
