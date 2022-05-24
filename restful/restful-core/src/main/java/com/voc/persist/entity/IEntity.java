package com.voc.persist.entity;

import com.voc.common.bean.IBean;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/07 16:34
 */
public interface IEntity<ID extends Serializable> extends IBean, Identify<ID>, ICreateInfo, IUpdateInfo, IFlagEntity, IStatusEntity {

}
