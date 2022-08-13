package com.voc.boot.gitlab.autoconfigure;

import com.voc.boot.gitlab.DefaultGitlabService;
import com.voc.boot.gitlab.GitlabProperties;
import com.voc.boot.gitlab.GitlabService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 16:39
 */
@Configuration
public class BeanConfig {

    @Bean
    @ConditionalOnMissingBean
    GitlabService gitlabService(GitlabProperties gitlabProperties) {
        return new DefaultGitlabService(gitlabProperties);
    }

}
