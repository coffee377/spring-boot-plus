package com.voc.common.api.entity;

import com.voc.common.api.bean.ITreeSort;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 20:22
 */
public interface TreeSortEntity<ID extends Serializable> extends Entity, ITreeSort<ID> {


}
