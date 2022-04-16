package com.voc.api.authority;

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
     * 权限掩码
     *
     * @return int
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
            return BigInteger.ONE;
        }
        return BigInteger.ONE.shiftLeft(mask);
    }
}
