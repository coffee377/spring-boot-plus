package com.voc.dingtalk.service;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/01 11:56
 */
public interface IRobotService extends IApiExecutor {

    /**
     * 发送信息
     */
    void send();
}
