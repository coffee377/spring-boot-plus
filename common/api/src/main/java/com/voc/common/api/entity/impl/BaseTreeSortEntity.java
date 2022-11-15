package com.voc.common.api.entity.impl;

import com.voc.common.api.entity.TreeSortEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 20:39
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class BaseTreeSortEntity<ID extends Serializable> extends BaseSortEntity implements TreeSortEntity<ID> {

    private ID parentId;

}
