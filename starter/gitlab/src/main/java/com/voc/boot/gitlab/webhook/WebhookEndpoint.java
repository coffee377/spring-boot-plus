package com.voc.boot.gitlab.webhook;

import com.voc.boot.gitlab.DefaultGitlabService;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.webhook.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/07 22:39
 */
@RestController
public class WebhookEndpoint {

    private static final String EVENT_HEADER = "X-Gitlab-Event";
    private static final String SECRET_TOKEN_HEADER = "X-Gitlab-Token";

    private static final String EVENT_UUID_HEADER = "X-Gitlab-Event-UUID";

    public static final String ENDPOINT_URL = "/actuator/gitlab/webhook";

//    @Resource
//    private WebHookHandler gitlabWebHookHandler;


    @PostMapping(ENDPOINT_URL)
    public void webhook(
            @RequestHeader(EVENT_HEADER) String event,
            @RequestHeader(SECRET_TOKEN_HEADER) String secretToken,
            @RequestHeader(EVENT_UUID_HEADER) String eventId,
            @RequestBody Event eventData
    ) {
        GitLabApi gitLabApi = new DefaultGitlabService(null).forPersonalAccessToken("");
//        gitLabApi.getProjectApi().createProject()
//        return gitlabWebHookHandler.handle(requestBody, event);
    }
}
