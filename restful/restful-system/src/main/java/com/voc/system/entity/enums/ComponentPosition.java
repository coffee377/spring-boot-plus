package com.voc.system.entity.enums;

import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 前端组件路径类型
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 22:41
 */
@Getter
@AllArgsConstructor
public enum ComponentPosition implements EnumDictItem<Integer> {
    SRC(0, "所有", "src 目录下组件"),
    COMPONENT(1, "组件", "src/components 目录下组件"),
    LAYOUT(2, "布局", "src/layouts 目录下布局组件"),
    PAGE(3, "页面", "src/pages 目录下页面组件");

    private final Integer value;
    private final String text;
    private final String comments;

}
