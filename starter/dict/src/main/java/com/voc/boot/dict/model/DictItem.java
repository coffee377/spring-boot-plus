package com.voc.boot.dict.model;

import com.voc.boot.dict.DataDictItem;
import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/06 23:02
 */
@Builder
@EqualsAndHashCode
public class DictItem<V> implements DataDictItem<V> {
    private V value;
    private String text;
    private String description;
    private Integer sort;

    @Override
    public Integer getSort() {
        return sort;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
