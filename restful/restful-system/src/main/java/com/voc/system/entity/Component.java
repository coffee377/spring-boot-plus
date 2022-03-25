package com.voc.system.entity;

import com.voc.restful.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 前端组件属性
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 13:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Component extends BaseEntity<String> {

    /**
     * 组件目录类型 layout | page | path
     */
    private String type;

    /**
     * 组件名称
     */
    private String component;

    /**
     * 组件属性配置
     */
    private Object props;

}
