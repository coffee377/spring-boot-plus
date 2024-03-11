package com.voc.dingtalk.model;

import com.voc.common.api.dict.EnumDictItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <a href="https://open.dingtalk.com/document/orgapp-server/message-types-and-data-format">消息类型与数据格式</a>
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/01 16:51
 */
@Getter
@AllArgsConstructor
public enum NotificationType implements EnumDictItem<Integer> {
    WORK(0, "工作通知", "是以企业工作通知会话中某个微应用的名义推送到员工的通知消息，例如生日祝福、入职提醒等"),
    GROUP_MESSAGE(1, "群消息", "是指可以调用接口以系统名义向群里推送群聊消息"),
    ORDINARY_MESSAGE(2, "普通消息", "指员工个人在使用应用时，可以通过界面操作的方式往群或其他人的会话里推送消息，例如发送日志的场景"),
    TASK(3, "任务类通知", "是指需要发送一条任务提醒给员工，比如审批任务等"),
    ;

    private final Integer value;

    /**
     * 消息名称
     */
    private final String label;

    /**
     * 消息描述
     */
    private final String description;
}
