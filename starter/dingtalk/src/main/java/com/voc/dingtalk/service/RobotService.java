package com.voc.dingtalk.service;

import com.voc.dingtalk.autoconfigure.model.Robot;

import java.util.List;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/24 11:15
 */
public interface RobotService {

    /**
     * 根据机器人名称获取编码
     *
     * @param robotName 机器人名称 {@link Robot#getName()}
     * @return 机器人编码
     */
    String getCodeByName(String robotName);

    /**
     * webhook 方式发送信息
     * <p>webhook 方式只支持在群聊会话</p>
     *
     * @param accessToken webhook 令牌
     * @param msgType     消息类型
     * @param content     消息内容
     */
    void sendWebhookMessage(String accessToken, String msgType, Object content);

    void sendMessage(String msgType, Object content, List<String> dingUserIds);
}
