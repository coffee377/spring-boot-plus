package com.voc.boot.gitlab;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 11:04
 */
public interface GitlabService {

    /**
     * Create a GitLabApi instance set up to interact with the GitLab server using the specified GitLab API version.
     *
     * @param tokenType the type of auth the token is for, PRIVATE or ACCESS
     * @param authToken the token to use for access to the API
     * @return GitLabApi
     */
    GitLabApi api(Constants.TokenType tokenType, String authToken);

    /**
     * Create a GitLabApi instance set up to interact with the GitLab server using the specified GitLab API version.
     *
     * @param tokenType   the type of auth the token is for, PRIVATE or ACCESS
     * @param authToken   the token to use for access to the API
     * @param secretToken use this token to validate received payloads
     * @return GitLabApi
     */
    GitLabApi api(Constants.TokenType tokenType, String authToken, String secretToken);


    /**
     * 个人访问令牌获取 API
     *
     * @param personalAccessToken the private token to use for access to the API
     * @return GitLabApi
     */
    GitLabApi forPersonalAccessToken(String personalAccessToken);

    /**
     * 个人访问令牌获取 API
     *
     * @param personalAccessToken the private token to use for access to the API
     * @param secretToken         use this token to validate received payloads
     * @return GitLabApi
     */
    GitLabApi forPersonalAccessToken(String personalAccessToken, String secretToken);
}
