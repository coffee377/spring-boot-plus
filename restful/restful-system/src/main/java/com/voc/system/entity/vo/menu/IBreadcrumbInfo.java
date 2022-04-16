package com.voc.system.entity.vo.menu;

/**
 * 面包屑描述接口
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 23:16
 */
public interface IBreadcrumbInfo {
    String getName();

    void setName(String name);

    String getIcon();

    void setIcon(String icon);

    String getHref();

    void setHref(String href);

    Boolean getExternal();

    void setExternal(Boolean external);

    Boolean getFirst();

    void setFirst(Boolean first);

    Boolean getLast();

    void setLast(Boolean last);
}
