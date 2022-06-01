package com.voc.common.authority;

import java.math.BigInteger;
import java.util.function.Supplier;

/**
 * 权限项描述
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:18
 */
public interface IAuthorityDescriptor extends Supplier<BigInteger> {

    /**
     * 权限名称
     *
     * @return String
     */
    String getName();

    /**
     * 权限掩码(正负数均表示同一权限对象)
     *
     * @return Integer
     */
    Integer getMask();

    /**
     * 权限大整数表示
     *
     * @return BigInteger
     */
    @Override
    default BigInteger get() {
        Integer mask = getMask();
        if (mask == null) {
            return BigInteger.ZERO;
        }
        return BigInteger.ONE.shiftLeft(Math.abs(mask) - 1);
    }
    
}
