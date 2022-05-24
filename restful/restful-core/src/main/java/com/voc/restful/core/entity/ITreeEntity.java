package com.voc.restful.core.entity;

import com.voc.persist.entity.IEntity;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/04/26 15:35
 */
public interface ITreeEntity<ID extends Serializable> extends IEntity<ID> {

    /**
     * 获取 id
     *
     * @return ID
     */
    ID getParentId();

    /**
     * 设置 id
     *
     * @param id ID
     */
    void setParentId(ID id);

}
