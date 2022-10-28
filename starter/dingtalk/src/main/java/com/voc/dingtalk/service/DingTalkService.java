package com.voc.dingtalk.service;

import com.voc.dingtalk.autoconfigure.model.App;
import com.voc.dingtalk.autoconfigure.model.Robot;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 11:10
 */
public interface DingTalkService {
    /**
     * 获取企业 ID
     *
     * @return String
     */
    String getCorpId();

    /**
     * 获取所有配置的应用
     *
     * @return App
     */
    List<App> getApps();

    /**
     * 获取所有配置的机器人
     *
     * @return Robot
     */
    List<Robot> getRobots();
}
