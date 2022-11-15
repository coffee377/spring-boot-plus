package com.voc.common.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 19:58
 */
public interface ITree<ID extends Serializable> extends Identify<ID> {

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

    default <C extends ITree<ID>> List<C> getChildren() {
        return null;
    }

}
