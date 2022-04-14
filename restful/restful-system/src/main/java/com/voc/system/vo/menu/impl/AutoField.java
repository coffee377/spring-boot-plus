package com.voc.system.vo.menu.impl;

import com.voc.restful.core.vo.JsonVO;
import com.voc.system.vo.menu.IAutoField;
import com.voc.system.vo.menu.IBreadcrumbInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 自动计算相关展示信息,处理器根据上下文环境自动处理
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 22:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AutoField extends JsonVO implements IAutoField {

    /**
     * 页面完整路径
     */
    private String[] paths;

    /**
     * 菜单链接地址
     */
    private String href;

    /**
     * 用于标记链接地址是否外链
     */
    private Boolean external;

    /**
     * 导航信息
     */
    private List<IBreadcrumbInfo> breadcrumbs;

}
