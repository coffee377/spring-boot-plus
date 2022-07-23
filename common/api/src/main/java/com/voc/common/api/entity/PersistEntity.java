package com.voc.common.api.entity;


import com.voc.common.api.bean.Identify;

import java.io.Serializable;

/**
 * 持久化实体标记
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/17 00:05
 */
public interface PersistEntity<ID extends Serializable> extends Identify<ID> {
}
