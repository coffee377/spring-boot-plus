package com.voc.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.voc.common.authority.IAuthorityDescriptor;
import com.voc.restful.core.entity.BaseEntity;
import com.voc.persist.entity.IEntity;
import com.voc.system.constant.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 持久化对象 - 数据
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 22:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = Table.DATA)
public class DataPO extends BaseEntity<String> implements IEntity<String>, IAuthorityDescriptor {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限掩码
     */
    private Integer mask;


}
