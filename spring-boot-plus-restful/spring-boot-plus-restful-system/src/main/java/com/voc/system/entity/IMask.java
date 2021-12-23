package com.voc.system.entity;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:18
 */
public interface IMask {
    /**
     * 获取操作名称
     *
     * @return String
     */
    String getName();

    /**
     * 设置操作名称
     *
     * @param name String
     */
    void setName(String name);

    /**
     * 获取操作掩码值
     *
     * @return int
     */
    int getMask();

    /**
     * 设置操作掩码值
     *
     * @param mask int
     */
    void setMask(int mask);
}
