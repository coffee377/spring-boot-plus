package com.voc.dingtalk.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/17 18:11
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.dingtalk")
public class DingTalkProperties implements InitializingBean {

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 企业ID
     */
    private String corpId;

    @NestedConfigurationProperty
    private Proxy proxy = new Proxy();

    /**
     * APP 配置
     */
    private Map<String, DingTalkApp> app = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        this.validate();
    }

    public void validate() {
        this.getApp().values().forEach(this::validateApp);
    }

    private void validateApp(DingTalkApp app) {
        if (!StringUtils.hasText(app.getAppKey())) {
            throw new IllegalStateException("appKey must not be empty.");
        }
        if (!StringUtils.hasText(app.getAppSecret())) {
            throw new IllegalStateException("appSecret must not be empty.");
        }
    }

}

