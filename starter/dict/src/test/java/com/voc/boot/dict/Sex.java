package com.voc.boot.dict;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/14 16:52
 */
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum Sex implements EnumDictItem<Integer> {
    MALE(1, "男"),
    FEMALE(2, "女"),
    ;

    private final Integer value;
    private final String label;

}
