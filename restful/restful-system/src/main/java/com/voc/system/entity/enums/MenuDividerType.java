package com.voc.system.entity.enums;

import com.voc.common.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单分隔符位置
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 20:18
 */
@Getter
@AllArgsConstructor
public enum MenuDividerType implements EnumDictItem<Integer> {
    NONE(0, "NONE", "无分隔符"),
    BEFORE(1, "BEFORE", "菜单之前"),
    AFTER(2, "AFTER", "菜单之后"),
    ALL(3, "ALL", "两者均含"),
    ;

    private final Integer value;
    private final String text;
    private final String comments;
}
