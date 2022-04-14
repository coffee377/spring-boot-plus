package com.voc.system.vo.menu;

import java.util.List;

/**
 * 自动计算相关展示信息,处理器根据上下文环境自动处理
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 23:20
 */
public interface IAutoField {
    String[] getPaths();

    void setPaths(String[] paths);

    String getHref();

    void setHref(String href);

    Boolean getExternal();

    void setExternal(Boolean external);

    List<IBreadcrumbInfo> getBreadcrumbs();

    void setBreadcrumbs(List<IBreadcrumbInfo> breadcrumbs);
}
