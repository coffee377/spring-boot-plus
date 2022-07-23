package com.voc.common.api.entity.impl;

import com.voc.common.api.entity.ISortEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/12 16:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseSortEntity<ID extends Serializable> extends BaseEntity<ID> implements ISortEntity<ID> {

    /**
     * 排序
     */
    private Integer sort;

}
