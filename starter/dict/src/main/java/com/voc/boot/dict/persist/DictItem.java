package com.voc.boot.dict.persist;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/06 23:02
 */
@Builder
@EqualsAndHashCode
public class DictItem<V> implements DataDictItem<V>, Comparable<DictItem<V>> {
    private String id;
    private V value;
    private String text;
    private String description;
    private Integer sort;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public int compareTo(DictItem<V> o) {
        Integer that = Optional.ofNullable(getSort()).orElse(0);
        Integer other = Optional.ofNullable(o.getSort()).orElse(0);
        return that.compareTo(other);
    }
}
