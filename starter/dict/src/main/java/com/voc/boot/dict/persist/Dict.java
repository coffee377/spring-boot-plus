package com.voc.boot.dict.persist;

import com.voc.common.api.dict.Dictionary;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/06 23:00
 */
@Builder
@EqualsAndHashCode
public class Dict<V> implements Dictionary<DictItem<V>> {
    private String code;
    private String name;
    private Collection<DictItem<V>> items;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<DictItem<V>> getItems() {
        return items;
    }
}
