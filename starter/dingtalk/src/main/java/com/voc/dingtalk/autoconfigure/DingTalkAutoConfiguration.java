package com.voc.dingtalk.autoconfigure;

import com.voc.dingtalk.autoconfigure.config.DingTalkCacheConfiguration;
import com.voc.dingtalk.autoconfigure.config.DingTalkNoticeConfiguration;
import com.voc.dingtalk.autoconfigure.config.DingTalkProxyConfiguration;
import com.voc.dingtalk.props.DingTalkProperties;
import com.voc.dingtalk.props.NoticeProperties;
import com.voc.dingtalk.service.DingTalkService;
import com.voc.dingtalk.service.impl.DefaultDingTalkService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/11/20 11:04
 */
@Configuration
@EnableConfigurationProperties(DingTalkProperties.class)
@ComponentScan(basePackages = "com.voc.dingtalk")
@ConditionalOnProperty(prefix = "spring.dingtalk", name = "enable", havingValue = "true", matchIfMissing = true)
@Import({DingTalkCacheConfiguration.class, DingTalkNoticeConfiguration.class, DingTalkProxyConfiguration.class})
public class DingTalkAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    DingTalkService dingTalkService(DingTalkProperties dingTalkProperties, NoticeProperties noticeProperties) {
        return new DefaultDingTalkService(dingTalkProperties, noticeProperties);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
