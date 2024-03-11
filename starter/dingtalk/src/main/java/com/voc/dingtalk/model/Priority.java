package com.voc.dingtalk.model;

import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/27 22:46
 */
@Getter
@AllArgsConstructor
public enum Priority implements EnumDictItem<Integer> {
    LOWEST(10, "较低"),
    ORDINARY(20, "普通"),
    HIGHER(30, "紧急"),
    HIGHEST(40, "非常紧急"),
    ;

    private final Integer value;
    private final String label;
    
}
