package com.voc.common.api.dict.enums;

import com.voc.common.api.dict.Dict;
import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;

/**
 * 数据使用状态
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/04/16 15:47
 */
@AllArgsConstructor
@Dict(name = "数据使用状态")
public enum UsingStatus implements EnumDictItem<Integer> {

    NORMAL(1, "正常", "正常使用"),
    LOCK(0, "锁定", "临时不使用"),
    DISABLE(-1, "禁用", "长期不使用");

    private final Integer value;
    private final String label;
    private final String description;


    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }


    @Override
    public String getDescription() {
        return description;
    }

}
