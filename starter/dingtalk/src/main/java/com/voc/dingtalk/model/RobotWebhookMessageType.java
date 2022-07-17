package com.voc.dingtalk.model;


import com.voc.common.dict.enumeration.EnumDictItem;

/**
 * <a href="https://open.dingtalk.com/document/group/message-types-and-data-format">消息类型与数据格式</a>
 *
 * @author gzp
 * @author Wu Yujie
 * @date 2022/6/21 20:01
 */
public enum RobotWebhookMessageType implements EnumDictItem<String> {
    TEXT("text", "文本消息"),
    IMAGE("image", "图片消息"),
    VOICE("voice", "语音消息"),
    FILE("file", "文件消息"),
    LINK("link", "链接消息"),
    OA("oa", "OA消息"),
    MARKDOWN("markdown", "MarkDown消息"),
    ACTION_CARD("action_card", "卡片消息"),
    ;

    private final String value;
    private final String text;

    RobotWebhookMessageType(String value, String display) {
        this.value = value;
        this.text = display;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getText() {
        return text;
    }

    public String getDisplay() {
        return text;
    }

}
