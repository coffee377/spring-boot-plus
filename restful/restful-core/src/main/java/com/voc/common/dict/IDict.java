package com.voc.common.dict;

import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 16:19
 */
public interface IDict<V, Item extends IDictItem<V>> {

    /**
     * 字典编码
     *
     * @return 编码
     */
    String getCode();

    /**
     * 字典名称
     *
     * @return 名称
     */
    String getName();

    /**
     * @return 字典项集合
     */
    Collection<Item> getItems();
}
