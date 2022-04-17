package com.voc.api;

import java.io.Serializable;
import java.util.Optional;

/**
 * 当前 Account 接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/04/16 16:18
 */
public interface IAccount<T, ID extends Serializable> {

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

    /**
     * 当前用户信息
     *
     * @return T
     */
    Optional<T> getUserInfo();

}
