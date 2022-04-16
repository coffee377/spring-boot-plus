package com.voc.system.entity.enums;

import com.voc.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单隐藏类型
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 20:10
 */
@Getter
@AllArgsConstructor
public enum MenuHiddenType implements EnumDictItem<Integer> {
    NONE(0, "NONE", "不隐藏任何节点"),
    ALL(1, "ALL", "隐藏自己和子节点"),
    CHILDREN(2, "CHILDREN", "隐藏子节点"),
    SELF(3, "SELF", "隐藏自己，并将子节点提升到与自己平级"),
    ;

    private Integer value;
    private String text;
    private final String comments;
}
