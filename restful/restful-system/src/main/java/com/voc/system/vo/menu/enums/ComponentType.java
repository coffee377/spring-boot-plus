package com.voc.system.vo.menu.enums;

import com.voc.api.dict.EnumDict;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 22:32
 * @see PathType
 */
@Getter
@AllArgsConstructor
public enum ComponentType implements EnumDict<String> {
    PATH("path", "所有", "src 目录下组件"),
    COMPONENT("component", "组件", "src/components 目录下组件"),
    LAYOUT("path", "布局", "src/layouts 目录下布局组件"),
    PAGE("path", "页面", "src/pages 目录下页面组件"),
    ;

    private String value;
    private final String text;
    private final String comments;

}
