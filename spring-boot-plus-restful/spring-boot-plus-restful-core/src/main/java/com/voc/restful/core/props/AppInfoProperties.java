package com.voc.restful.core.props;

import com.voc.restful.core.banner.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/17 11:02
 * @see com.voc.restful.core.listener.AppInfoEnvironmentPostProcessor
 */
@Getter
@Setter
//@Configuration
@PropertySource(name = AppInfoProperties.NAME, value = "classpath:META-INF/app-info.yml", ignoreResourceNotFound = true,
        encoding = "utf-8", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "spring.application")
public class AppInfoProperties {
    public final static String NAME = "app-info";

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用版本
     */
    private String version;

}
