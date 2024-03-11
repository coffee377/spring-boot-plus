package com.voc.security.oauth2.enums;

import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/11 19:14
 */
@Getter
@AllArgsConstructor
public enum TokenFormat implements EnumDictItem<Integer> {
    JWT(0, "自包含令牌"),
    OPAQUE(1, "不透明令牌"),
    ;
    private final Integer value;
    private final String label;
}
