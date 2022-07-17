package api.entity.impl;

import api.dict.enums.DataFlag;
import api.dict.enums.UsingStatus;
import api.entity.ICommonEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/16 20:12
 */
@Data
@EqualsAndHashCode
public abstract class CommonEntity implements ICommonEntity {
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
