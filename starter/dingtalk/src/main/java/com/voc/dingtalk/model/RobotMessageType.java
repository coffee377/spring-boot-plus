package com.voc.dingtalk.model;

import com.voc.common.dict.enumeration.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 20:47
 */
@Getter
@AllArgsConstructor
public enum RobotMessageType implements EnumDictItem<String> {
    INTERFACE("接口发送", new String[]{"text", "link", "markdown", "actionCard", "image"}),

    WEBHOOK("Webhook发送", new String[]{"text", "link", "markdown", "actionCard", "feedCard"}),
    ;

    /**
     * 发送消息方式
     */
    private final String text;

    /**
     * 支持的消息格式
     */
    private final String[] format;


    @Override
    public String getValue() {
        return null;
    }

    @Override
    public String getText() {
        return text;
    }
}
