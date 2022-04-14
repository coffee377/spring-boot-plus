package com.voc.system.vo.menu.enums;

import com.voc.api.dict.EnumDict;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 23:55
 */
@Getter
@AllArgsConstructor
public enum FileSuffix implements EnumDict<String> {
    VUE,
    TSX,
    JSX,
    TS,
    JS,
    ;

    @Override
    public String getValue() {
        return this.name().toLowerCase();
    }

    @Override
    public String getText() {
        return this.name();
    }
}
