package com.voc.common.api.dict.enums;

import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据使用状态
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/04/16 15:47
 */
@Getter
@AllArgsConstructor
public enum UsingStatus implements EnumDictItem<Integer> {

    NORMAL(1, "正常", "正常"),
    LOCK(0, "锁定", "临时不使用"),
    DISABLE(-1, "禁用", "长期不使用");

    private final Integer value;
    private final String text;
    private final String description;

}
