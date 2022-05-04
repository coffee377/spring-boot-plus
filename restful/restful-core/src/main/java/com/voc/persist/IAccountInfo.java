package com.voc.persist;

import java.io.Serializable;
import java.util.Optional;

/**
 * 当前登录用户账号信息接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/04/16 16:18
 */
public interface IAccountInfo<ID extends Serializable, T> {

    /**
     * 当前用户信息
     *
     * @return T
     */
    Optional<T> getUserInfo();

    /**
     * 当前用户 ID
     *
     * @return K
     */
    ID getUserId();

    /**
     * 当前用户名
     *
     * @return String
     */
    String getUserName();

}
