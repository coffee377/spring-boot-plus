package com.voc.boot.gitlab.autoconfigure;

import com.voc.boot.gitlab.GitlabProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 10:37
 */
@Import(BeanConfig.class)
@EnableConfigurationProperties(GitlabProperties.class)
public class GitlabAutoConfiguration {


}
