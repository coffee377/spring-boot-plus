package com.voc.boot.gitlab;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 10:47
 */
public class DefaultGitlabService implements GitlabService {

    private final GitlabProperties gitlabProperties;

    public DefaultGitlabService(GitlabProperties gitlabProperties) {
        this.gitlabProperties = gitlabProperties;
    }

    @Override
    public GitLabApi api(Constants.TokenType tokenType, String authToken) {
        return new GitLabApi(gitlabProperties.getVersion(), gitlabProperties.getHost(), tokenType, authToken, null);
    }

    @Override
    public GitLabApi api(Constants.TokenType tokenType, String authToken, String secretToken) {
        return new GitLabApi(gitlabProperties.getVersion(), gitlabProperties.getHost(), tokenType, authToken, secretToken);
    }

    @Override
    public GitLabApi forPersonalAccessToken(String personalAccessToken) {
        return new GitLabApi(gitlabProperties.getHost(), personalAccessToken);
    }

    @Override
    public GitLabApi forPersonalAccessToken(String personalAccessToken, String secretToken) {
        return new GitLabApi(gitlabProperties.getHost(), personalAccessToken, secretToken);
    }
}
