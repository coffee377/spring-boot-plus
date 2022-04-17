package com.voc.restful.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.voc.api.enums.DataFlag;
import com.voc.api.enums.UsingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 16:15
 */
@Data
@EqualsAndHashCode
public abstract class BaseEntity<ID extends Serializable> implements IEntity<ID> {

    /**
     * 主键 ID
     */
    protected ID id;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Instant createdAt;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Instant updatedAt;

    /**
     * 数据增删改标识
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private DataFlag flag;

    /**
     * 数据使用状态
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private UsingStatus status;

}
