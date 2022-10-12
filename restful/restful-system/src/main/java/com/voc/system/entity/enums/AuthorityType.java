package com.voc.system.entity.enums;

import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限类型
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/16 11:46
 */
@Getter
@AllArgsConstructor
public enum AuthorityType implements EnumDictItem<Integer> {
    MENU(0, "菜单", "菜单权限"),
    INTERFACE(1, "接口", "接口权限"),
    ACTION(2, "操作", "操作权限"),
    DATA(3, "数据", "数据权限");

    private final Integer value;
    private final String text;
    private final String comments;

}
