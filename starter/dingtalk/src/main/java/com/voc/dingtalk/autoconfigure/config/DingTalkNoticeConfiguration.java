package com.voc.dingtalk.autoconfigure.config;

import com.voc.dingtalk.props.NoticeProperties;
import com.voc.dingtalk.service.RobotService;
import com.voc.dingtalk.service.impl.DefaultRobotService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/24 10:59
 */
@Configuration
@EnableConfigurationProperties(NoticeProperties.class)
public class DingTalkNoticeConfiguration {

    @Bean
    @ConditionalOnMissingBean
    RobotService robotService(NoticeProperties noticeProperties) {
        return new DefaultRobotService(noticeProperties);
    }

}
