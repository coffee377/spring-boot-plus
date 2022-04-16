package com.voc.system.entity.po;

import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.IEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 持久化对象 - 数据字典项
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 23:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DictItemPO extends BaseEntity<String> implements IEntity<String> {

    /**
     * 字典 ID
     */
    private String dictId;

    /**
     * 实际值
     */
    private String value;

    /**
     * 显示值
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;
}
