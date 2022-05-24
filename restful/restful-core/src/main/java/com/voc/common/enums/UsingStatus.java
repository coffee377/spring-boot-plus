package com.voc.common.enums;

import com.voc.common.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 使用状态
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/04/16 15:47
 */
@Getter
@AllArgsConstructor
public enum UsingStatus implements EnumDictItem<Integer> {

    NORMAL(1, "正常"),
    LOCK(0, "锁定"),
    DISABLE(-1, "禁用");

    private final Integer value;
    private final String text;

}
