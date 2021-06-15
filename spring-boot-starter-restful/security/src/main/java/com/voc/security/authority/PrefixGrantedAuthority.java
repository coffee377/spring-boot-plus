package com.voc.security.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/15 09:52
 */
public class PrefixGrantedAuthority implements GrantedAuthority {

    public static String SEPARATOR = "_";

    private final String prefix;

    private final String name;

    public PrefixGrantedAuthority(String prefix, String name) {
        Assert.notNull(name, "name cannot be null");
        this.prefix = prefix;
        if (StringUtils.hasText(prefix) && name.startsWith(prefixWithSeparator(prefix))) {
            this.name = name.replaceFirst(prefixWithSeparator(prefix), "");
        } else {
            this.name = name;
        }
    }

    private String prefixWithSeparator(String prefix) {
        return prefix + SEPARATOR;
    }

    public PrefixGrantedAuthority(String name) {
        this(null, name);
    }

    @Override
    public String getAuthority() {
        if (StringUtils.hasText(prefix)) {
            return String.join("_", prefix, name);
        }
        return name;
    }

    @Override
    public int hashCode() {
        return this.getAuthority().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof PrefixGrantedAuthority && this.getAuthority().equals(((PrefixGrantedAuthority) obj).getAuthority());
        }
    }

    @Override
    public String toString() {
        return this.getAuthority();
    }
}
