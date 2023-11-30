package com.voc.common.api.func;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/12 21:10
 */
@Getter
@AllArgsConstructor
public enum Action implements FunctionPoint {
    QUERY("查询"),
    ADD("添加"),
    DELETE("删除"),
    UPDATE("更新"),
    EXPORT("导出")
    ;

    private final String description;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public int getPosition() {
        return ordinal() + 1;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
