package com.voc.boot.dict;

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
//@DictItemSerialize(SerializeType.TEXT) // 定义 Sex 默认序列化类型
public enum Sex implements EnumDictItem<Integer> {
    MALE(1, "男"),
    FEMALE(2, "女"),
    ;

    private final Integer value;
    private final String text;

}
