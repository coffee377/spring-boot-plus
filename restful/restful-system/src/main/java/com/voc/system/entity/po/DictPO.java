package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.restful.core.entity.IEntity;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 持久化对象 - 数据字典
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 23:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.DICT)
public class DictPO extends BaseEntity<String> implements IEntity<String> {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 分类
     */
    private String tag;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;
}
