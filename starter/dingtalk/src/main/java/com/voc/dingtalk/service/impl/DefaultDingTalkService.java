package com.voc.dingtalk.service.impl;

import com.voc.dingtalk.autoconfigure.model.App;
import com.voc.dingtalk.autoconfigure.model.Robot;
import com.voc.dingtalk.props.DingTalkProperties;
import com.voc.dingtalk.props.NoticeProperties;
import com.voc.dingtalk.cache.CacheInitListener;
import com.voc.dingtalk.cache.DingTalkCache;
import com.voc.dingtalk.service.DingTalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 11:12
 */
@Slf4j
public class DefaultDingTalkService implements DingTalkService {

    private final DingTalkProperties dingTalkProperties;
    private final NoticeProperties noticeProperties;

    public DefaultDingTalkService(DingTalkProperties dingTalkProperties, NoticeProperties noticeProperties) {
        this.dingTalkProperties = dingTalkProperties;
        this.noticeProperties = noticeProperties;
    }

    @Override
    public String getCorpId() {
        return dingTalkProperties.getCorpId();
    }

    /**
     * 缓存所有钉钉应用配置信息
     * <p>在应用启动完成后 {@link CacheInitListener} 清除 {@link DingTalkCache#APP} 缓存</p>
     *
     * @return List<App>
     */
    @Override
    @Cacheable(cacheNames = DingTalkCache.APP, key = "'all'")
    public List<App> getApps() {
        List<App> apps = Optional.ofNullable(dingTalkProperties.getApp())
                .orElseGet(HashMap::new).entrySet()
                .stream().map(entry -> {
                    String name = entry.getKey();
                    App app = entry.getValue();
                    if (!StringUtils.hasText(app.getName())) {
                        app.setName(name);
                    }
                    return app;
                }).collect(Collectors.toList());
        log.debug("所有 APP 配置信息：{}", apps);
        return apps;
    }

    @Override
    @Cacheable(cacheNames = DingTalkCache.ROBOT, key = "'all'")
    public List<Robot> getRobots() {
        List<Robot> robots = Optional.ofNullable(noticeProperties.getRobots())
                .orElseGet(HashMap::new).entrySet()
                .stream().map(entry -> {
                    String name = entry.getKey();
                    Robot robot = entry.getValue();
                    if (!StringUtils.hasText(robot.getName())) {
                        robot.setName(name);
                    }
                    return robot;
                }).collect(Collectors.toList());
        log.debug("所有 Robot 配置信息：{}", robots);
        return robots;
    }
}
