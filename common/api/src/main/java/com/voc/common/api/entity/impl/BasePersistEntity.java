package com.voc.common.api.entity.impl;

import com.voc.common.api.entity.PersistEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/08 16:20
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class BasePersistEntity<ID extends Serializable> extends BaseCommonEntity implements PersistEntity<ID> {
    /**
     * 主键 ID
     */
    protected ID id;
}
