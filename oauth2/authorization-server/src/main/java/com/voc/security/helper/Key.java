package com.voc.security.helper;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/04 21:59
 */
@FunctionalInterface
public interface Key {

    /**
     * Key 生成器
     *
     * @return String
     */
    String create();
}
