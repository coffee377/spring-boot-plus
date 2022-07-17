package com.voc.common.dict.persist;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 16:40
 */
@Builder
@EqualsAndHashCode
public class DictItem<V> implements DataDictItem<V>, Comparable<DictItem<V>> {
    /**
     * 字典项标识
     */
    private String id;

    /**
     * 显示值
     */
    private String name;

    /**
     * 实际值
     */
    private V value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    @Override
    public Integer getIndex() {
        return sort;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(@NotNull DictItem<V> o) {
        Integer that = Optional.ofNullable(getIndex()).orElse(0);
        Integer other = Optional.ofNullable(o.getIndex()).orElse(0);
        return that.compareTo(other);
    }
}
