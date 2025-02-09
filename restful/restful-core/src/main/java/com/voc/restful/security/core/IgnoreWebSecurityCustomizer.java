package com.voc.restful.security.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/03/29 09:14
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class IgnoreWebSecurityCustomizer implements WebSecurityCustomizer, PriorityOrdered {
    public static final String SLASH = "/";

    private SecurityProperties securityProperties;

    @Autowired(required = false)
    public void setResourceServerProperties(SecurityProperties resourceServerProperties) {
        this.securityProperties = resourceServerProperties;
    }

    @Override
    public void customize(WebSecurity web) {
        /* 自定义不需要验证权限的URL */
        String[] ignoreUrls = this.ignoreUrls(securityProperties.getIgnore(), "/favicon.ico", "/ping", "/error");
        web.ignoring().antMatchers(ignoreUrls);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private String[] ignoreUrls(List<String> ignore, String... extraUrl) {
        List<String> list = Optional.ofNullable(ignore).orElse(new LinkedList<>());
        Set<String> set = new HashSet<>(list);
        set.addAll(Arrays.asList(extraUrl));
        return set.stream().map(url -> {
            if (!url.startsWith(SLASH)) {
                return SLASH + url;
            }
            return url;
        }).collect(Collectors.toList()).toArray(new String[]{});
    }
}
