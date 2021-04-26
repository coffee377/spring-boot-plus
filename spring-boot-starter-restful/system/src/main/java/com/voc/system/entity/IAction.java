package com.voc.system.entity;

import com.voc.restful.core.entity.IEntity;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 09:12
 */
public interface IAction<ID extends Serializable> extends IEntity<ID>, IMask {

}
