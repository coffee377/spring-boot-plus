package com.voc.boot.dict.persist;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;

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
    private String label;
    private String description;
    private String code;
    private Integer sort;
    private Boolean disabled;

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
    public String getLabel() {
        return label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Boolean isDisabled() {
        return disabled;
    }

    @Override
    public int compareTo(DictItem<V> other) {
        if (other == null) return 1;
        Integer sort1 = getSort();
        Integer sort2 = other.getSort();
        if (sort1 != null && sort2 != null) {
            return sort1.compareTo(sort2);
        } else {
            // todo 比较还是有问题
            if (sort1 == null && sort2 != null) {
                return -1;
            }
//            if (sort2 == null && sort2 = null) {
//                return 1;
//            }
        }
        return 0;
    }
}
