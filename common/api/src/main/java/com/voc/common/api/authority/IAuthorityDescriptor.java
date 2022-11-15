package com.voc.common.api.authority;

import com.voc.common.api.func.FunctionPoint;

/**
 * 权限项描述
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:18
 */
@Deprecated
public interface IAuthorityDescriptor extends FunctionPoint {

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
    @Deprecated
    default Integer getMask() {
        return getPosition();
    }

}
