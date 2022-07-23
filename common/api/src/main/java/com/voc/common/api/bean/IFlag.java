package com.voc.common.api.bean;


import com.voc.common.api.dict.enums.DataFlag;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:20
 */
public interface IFlag {

    /**
     * 获取数据状态标识
     *
     * @return 状态标识
     */
    DataFlag getFlag();

    /**
     * 数据状态赋值
     *
     * @param dataFlag DataFlag
     */
    void setFlag(DataFlag dataFlag);

}
