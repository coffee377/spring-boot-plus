package com.voc.dingtalk.service;

import java.util.List;

/**
 * <a href="https://open.dingtalk.com/document/group/message-types-and-data-format">机器人消息类型和数据格式</a>
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/01 11:56
 */
public interface IRobotService extends IApiExecutor {

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
