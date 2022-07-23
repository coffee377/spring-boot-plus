package com.voc.common.api.entity.impl;

import com.voc.common.api.entity.ITreeSortEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 20:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseTreeSortEntity<ID extends Serializable> extends BaseSortEntity<ID> implements ITreeSortEntity<ID> {

    private ID parentId;

}
