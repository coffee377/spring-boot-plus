package com.voc.boot.gitlab.webhook.event;

import org.gitlab4j.api.webhook.IssueEvent;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 19:28
 */
public class ConfidentialIssueEvent extends IssueEvent {

    public static final String X_GITLAB_EVENT = "Confidential Issue Hook";
    public static final String OBJECT_KIND = "confidential_issue";

    @Override
    public String getObjectKind() {
        return OBJECT_KIND;
    }
}
