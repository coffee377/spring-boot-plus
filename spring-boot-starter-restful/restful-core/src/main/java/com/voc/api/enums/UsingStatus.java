package com.voc.api.enums;

import com.voc.api.dict.EnumDict;

/**
 * 使用状态枚举类
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/04/16 15:47
 */
public enum UsingStatus implements EnumDict {

    NORMAL("正常"),
    LOCK("锁定"),
    DISABLE("禁用");

    private final String text;

    UsingStatus(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }


}
