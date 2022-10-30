package com.voc.boot.gitlab;

import lombok.Data;
import org.apache.http.util.Asserts;
import org.gitlab4j.api.GitLabApi;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 10:34
 */
@Data
@ConfigurationProperties(GitlabProperties.PREFIX)
public class GitlabProperties implements InitializingBean {


    public static final String PREFIX = "gitlab";

    /**
     * enable gitlab
     */
    private Boolean enable = true;

    /**
     * the URL of the GitLab server
     */
    private String host;

    /**
     * Specifies the version of the GitLab API to communicate with.
     */
    private GitLabApi.ApiVersion version = GitLabApi.ApiVersion.V4;

    @Override
    public void afterPropertiesSet() throws Exception {
        Asserts.notNull(host, "gitlab host can not be null");
    }
}
