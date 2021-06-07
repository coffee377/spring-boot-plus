package com.voc.restful.core.entity;

import com.voc.restful.core.bean.IJsonBean;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/02/07 16:34
 */
public interface IEntity<ID extends Serializable> extends IJsonBean, ICreateInfo, IUpdateInfo {
    /**
     * 获取 id
     *
     * @return ID
     */
    ID getId();

    /**
     * 设置 id
     *
     * @param id ID
     */
    void setId(ID id);

}
