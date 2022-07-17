package com.voc.dingtalk.service;

import com.voc.dingtalk.autoconfigure.App;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 11:10
 */
public interface IDingTalkService {
    /**
     * 获取企业ID
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
}
