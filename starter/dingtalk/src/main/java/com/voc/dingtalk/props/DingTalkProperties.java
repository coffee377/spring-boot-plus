package com.voc.dingtalk.props;

import com.voc.dingtalk.autoconfigure.model.App;
import lombok.Data;
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
@Data
@ConfigurationProperties(prefix = "dingtalk")
public class DingTalkProperties implements InitializingBean {
    private int primaryAppCount;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 企业ID
     */
    private String corpId;

    @NestedConfigurationProperty
    private ProxyProperties proxy = new ProxyProperties();

    /**
     * APP 配置
     */
    private Map<String, App> app = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        this.validate();
    }

    public void validate() {
        this.getApp().values().forEach(this::validateApp);
        this.validatePrimaryApp();
    }

    private void validatePrimaryApp() {
        if (this.primaryAppCount > 1) {
            log.error("只允许一个主应用", new IllegalStateException("Only one active application is allowed"));
        }
    }

    private void validateApp(App app) {
        if (!StringUtils.hasText(app.getAppKey())) {
            throw new IllegalStateException("appKey must not be empty.");
        }
        if (!StringUtils.hasText(app.getAppSecret())) {
            throw new IllegalStateException("appSecret must not be empty.");
        }
        if (app.isPrimary()) {
            this.primaryAppCount += 1;
        }
    }

}

