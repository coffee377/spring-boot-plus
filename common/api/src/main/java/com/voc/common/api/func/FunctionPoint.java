package com.voc.common.api.func;

import com.voc.common.api.authority.IAuthorities;

import java.math.BigInteger;
import java.util.function.Supplier;

/**
 * 功能点描述
 *
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.2
 */
public interface FunctionPoint extends IAuthorities {

    /**
     * 功能点名称
     *
     * @return 名称
     */
    String getName();

    /**
     * 功能点位置，必须大于零，且不允许重复
     *
     * @return 位置索引
     */
    int getPosition();

    /**
     * 功能点描述
     *
     * @return 描述信息
     */
    String getDescription();

    /**
     * 权限大整数表示
     *
     * @return BigInteger
     */
    @Override
    default BigInteger get() {
        int position = getPosition();
        if (position <= 0) throw new FunctionException("-1", "功能点位置 position 的值必须为正整数");
        return BigInteger.ONE.shiftLeft(position - 1);
    }

}
