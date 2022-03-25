package com.voc.api.dict;

import com.voc.api.enums.ActionType;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/12 17:37
 */
public class DefaultActions implements IMasks {
    private final BigInteger masks;

    public static IMasks ALL = new DefaultActions(Arrays.stream(ActionType.values()).map(EnumDict::getMask).reduce(BigInteger.ZERO, BigInteger::add));

    public DefaultActions(BigInteger masks) {
        this.masks = masks;
    }

    public DefaultActions(String masks) {
        this.masks = new BigInteger(masks);
    }

    public DefaultActions() {
        this(BigInteger.ZERO);
    }

    @Override
    public BigInteger get() {
        return masks;
    }

}
