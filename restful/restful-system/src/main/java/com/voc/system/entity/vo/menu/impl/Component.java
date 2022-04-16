package com.voc.system.entity.vo.menu.impl;

import com.voc.system.entity.vo.menu.IComponent;
import com.voc.system.entity.enums.ComponentFileSuffix;
import com.voc.system.entity.enums.ComponentPosition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 组件相关属性
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 22:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Component extends RouteInfo implements IComponent {
    /**
     * 组件所在目录
     */
    private ComponentPosition position;

    /**
     * 使用组件名称
     * 组件 src/components 目录下组件名称，如 A
     * 布局 src/layouts 目录下布局名称，如 BasicLayout
     * 页面 src/pages 目录下页面名称，如 About
     */
    private String component;

    /**
     * 组件后缀名
     */
    private ComponentFileSuffix suffix;

    /**
     * 组件属性配置
     */
    private Map<String, Object> configuration;

    /**
     * 重定向地址
     */
    private String redirect;
}
