package com.voc.persist.entity;

import com.voc.common.enums.DataFlag;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/06/27 22:20
 */
public interface IFlagEntity {

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
