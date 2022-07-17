package com.voc.common.dict.persist;

import com.voc.common.dict.IDict;
import lombok.Builder;

import java.util.Set;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 16:48
 */
@Builder
public class Dict<V> implements IDict<V, DictItem<V>> {

    private String code;
    private String name;

    private Set<DictItem<V>> items;


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<DictItem<V>> getItems() {
        return items;
    }
}
