package com.voc.system.entity.vo.menu.impl;

import com.voc.restful.core.vo.JsonVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 面包屑描述
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 22:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BreadcrumbInfo extends JsonVO {
    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 跳转地址
     */
    private String href;

    /**
     * 标记跳转地址地址是否外链
     */
    private Boolean external;

    /**
     * 是否是第一个节点
     */
    private Boolean first;

    /**
     * 是否最后一个节点
     */
    private Boolean last;

}
