package com.voc.common.api.entity.impl;

import com.voc.common.api.entity.SortEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/07/12 16:47
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class BaseSortEntity extends BaseEntity implements SortEntity {

    /**
     * 排序
     */
    private Integer sort;

}
