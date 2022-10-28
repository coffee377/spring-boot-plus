package com.voc.dingtalk.service.impl;

import com.voc.dingtalk.props.NoticeProperties;
import com.voc.dingtalk.autoconfigure.model.Robot;
import com.voc.dingtalk.service.RobotService;

import java.util.List;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/24 11:21
 */
public class DefaultRobotService implements RobotService {
    private final NoticeProperties noticeProperties;

    public DefaultRobotService(NoticeProperties noticeProperties) {
        this.noticeProperties = noticeProperties;
    }

    @Override
    public String getCodeByName(String robotName) {
        Robot robot = noticeProperties.getRobots().getOrDefault(robotName, new Robot());
        return robot.getAppKey();
    }

    @Override
    public void sendWebhookMessage(String accessToken, String msgType, Object content) {

    }

    @Override
    public void sendMessage(String msgType, Object content, List<String> dingUserIds) {

    }
}
