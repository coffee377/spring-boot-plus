package com.voc.restful.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 16:15
 */
@Data
public abstract class BaseEntity<ID extends Serializable> implements IEntity<ID> {

    /**
     * 主键 ID
     */
    protected ID id;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Instant createdAt;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Instant updatedAt;

}
