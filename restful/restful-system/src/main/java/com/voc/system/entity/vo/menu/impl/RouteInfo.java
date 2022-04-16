package com.voc.system.entity.vo.menu.impl;

import com.voc.restful.core.vo.JsonVO;
import com.voc.system.entity.vo.menu.IRouteInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 路由信息
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 22:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RouteInfo extends JsonVO implements IRouteInfo {
    /**
     * 路由名称
     * 动态替换路由使用
     */
    private String routeName;
}
