package com.voc.restful.core.entity;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/05 01:34
 */
public interface Identify<ID extends Serializable> {
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
