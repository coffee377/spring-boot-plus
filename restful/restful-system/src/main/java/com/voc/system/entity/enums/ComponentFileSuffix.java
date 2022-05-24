package com.voc.system.entity.enums;

import com.voc.common.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 前端组件文件后缀名
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/04/14 23:55
 */
@Getter
@AllArgsConstructor
public enum ComponentFileSuffix implements EnumDictItem<String> {
    VUE, TSX, JSX, TS, JS;

    @Override
    public String getValue() {
        return this.name().toLowerCase();
    }

    @Override
    public String getText() {
        return this.name();
    }
}
