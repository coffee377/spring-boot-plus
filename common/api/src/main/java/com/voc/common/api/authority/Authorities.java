package com.voc.common.api.authority;

import java.math.BigInteger;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/12 00:02
 */
@Deprecated
public class Authorities implements IAuthorities {

    private final BigInteger value;

    public Authorities(String value) {
        this.value = new BigInteger(value);
    }

    public static Authorities of(String value) {
        return new Authorities(value);
    }

    public static Authorities of(int value) {
        return new Authorities(String.valueOf(value));
    }

    public static Authorities of(long value) {
        return new Authorities(String.valueOf(value));
    }

    @Override
    public BigInteger get() {
        return value;
    }
}
