package com.voc.common.bean;


import com.voc.common.dict.enumeration.UsingStatus;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:21
 */
public interface IStatus {

    /**
     * 获取数据使用状态
     *
     * @return 状态标识
     */
    UsingStatus getStatus();

    /**
     * 数据使用状态赋值
     *
     * @param usingStatus UsingStatus
     */
    void setStatus(UsingStatus usingStatus);

}
