package com.voc.common.api.entity.impl;

import com.voc.common.api.dict.enums.DataFlag;
import com.voc.common.api.dict.enums.UsingStatus;
import com.voc.common.api.entity.CommonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 20:12
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCommonEntity extends BaseEntity implements CommonEntity {
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

    /**
     * 数据增删改标识
     */
    private DataFlag flag;

    /**
     * 数据使用状态
     */
    private UsingStatus status;
}
