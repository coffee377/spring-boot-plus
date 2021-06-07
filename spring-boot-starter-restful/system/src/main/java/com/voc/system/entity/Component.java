package com.voc.system.entity;

import com.voc.restful.core.bean.IBean;
import lombok.Data;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 13:57
 */
@Data
public class Component implements IBean {

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
