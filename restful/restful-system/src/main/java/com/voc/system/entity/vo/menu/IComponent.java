package com.voc.system.entity.vo.menu;

import com.voc.system.entity.enums.ComponentFileSuffix;
import com.voc.system.entity.enums.ComponentPosition;

import java.util.Map;

/**
 * 组件描述
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 23:23
 */
public interface IComponent extends IRouteInfo {
    /**
     * 获取组件路径类型
     *
     * @return PathType
     */
    ComponentPosition getPosition();

    /**
     * 设置组件路径类型
     *
     * @param pathType PathType
     */
    void setPosition(ComponentPosition pathType);

    /**
     * 获取组件名称
     *
     * @return String
     */
    String getComponent();

    /**
     * 设置组件名称
     *
     * @param name String
     */
    void setComponent(String name);

    /**
     * 获取组件文件后缀名
     *
     * @return FileSuffix
     */
    ComponentFileSuffix getSuffix();

    /**
     * 设置组件文件后缀名
     *
     * @param suffix FileSuffix
     */
    void setSuffix(ComponentFileSuffix suffix);

    /**
     * 获取组件配置
     *
     * @return Map<String, Object>
     */
    Map<String, Object> getConfiguration();

    /**
     * 设置组件配置
     *
     * @param configuration Map<String, Object>
     */
    void setConfiguration(Map<String, Object> configuration);

    /**
     * 获取重定向地址
     *
     * @return String
     */
    String getRedirect();

    /**
     * 设置重定向地址
     *
     * @param redirect String
     */
    void setRedirect(String redirect);

}
