package com.voc.common.api.entity.impl;

import com.voc.common.api.entity.IEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 16:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity<ID extends Serializable> extends CommonEntity implements IEntity<ID> {

    /**
     * 主键 ID
     */
    protected ID id;

}
