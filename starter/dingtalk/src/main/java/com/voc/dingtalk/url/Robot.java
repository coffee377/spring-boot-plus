package com.voc.dingtalk.url;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 机器人相关接口地址
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/07/03 10:40
 */
@Getter
@AllArgsConstructor
public enum Robot implements UrlPath {
    /**
     * 机器人 webhook 发送信息
     */
    ROBOT_SEND_MESSAGE("/robot/send"),
    ;

    private final String path;
}
