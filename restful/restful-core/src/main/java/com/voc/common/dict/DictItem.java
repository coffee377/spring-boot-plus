package com.voc.common.dict;

import lombok.Data;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 16:40
 */
@Data
public class DictItem<V> implements DataDictItem<V> {
    private String id;

    /**
     * 实际值
     */
    private V value;

    /**
     * 显示值
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    @Override
    public String getText() {
        return name;
    }

    @Override
    public Integer getIndex() {
        return sort;
    }

    @Override
    public String getComments() {
        return remark;
    }

}
