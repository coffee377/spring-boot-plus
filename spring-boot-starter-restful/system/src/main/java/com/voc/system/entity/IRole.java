package com.voc.system.entity;

import com.voc.restful.core.entity.IEntity;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/05 08:57
 */
public interface IRole<ID extends Serializable> extends IEntity<ID> {

    /**
     * 角色编码
     *
     * @return String
     */
    String getCode();

    /**
     * 角色名称
     *
     * @return String
     */
    String getName();
}
