package com.voc.common.bean;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 16:03
 */
public interface ICreateInfo {
    /**
     * 获取创建人
     *
     * @return String
     */
    String getCreatedBy();

    /**
     * 设置创建人
     *
     * @param creator 创建人ID
     */
    void setCreatedBy(String creator);

    /**
     * 获取创建时间
     *
     * @return Instant
     */
    Instant getCreatedAt();

    /**
     * 设置创建时间
     *
     * @param time 创建时间
     */
    void setCreatedAt(Instant time);

}
