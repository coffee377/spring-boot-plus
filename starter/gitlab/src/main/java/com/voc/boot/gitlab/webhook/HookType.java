package com.voc.boot.gitlab.webhook;

import com.voc.boot.gitlab.webhook.event.ConfidentialIssueEvent;
import com.voc.boot.gitlab.webhook.event.ConfidentialNoteEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.gitlab4j.api.webhook.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/07 22:44
 */
@Getter
@AllArgsConstructor
public enum HookType {

    PUSH(PushEvent.class, PushEvent.OBJECT_KIND, PushEvent.X_GITLAB_EVENT, "推送事件", "推送到仓库"),
    TAG_PUSH(TagPushEvent.class, TagPushEvent.OBJECT_KIND, TagPushEvent.X_GITLAB_EVENT, "标签推送事件", "新标签被推送到仓库"),
    NOTE(NoteEvent.class, NoteEvent.OBJECT_KIND, NoteEvent.X_GITLAB_EVENT, "评论", "一条评论被添加到议题或合并请求"),
    CONFIDENTIAL_NOTE(ConfidentialNoteEvent.class, ConfidentialNoteEvent.OBJECT_KIND, ConfidentialNoteEvent.X_GITLAB_EVENT, "私密评论", "一条评论被添加到机密议题"),
    ISSUE(IssueEvent.class, IssueEvent.OBJECT_KIND, IssueEvent.X_GITLAB_EVENT, "议题事件", "已创建、更新、关闭或重新打开议题"),
    CONFIDENTIAL_ISSUE(ConfidentialIssueEvent.class, ConfidentialIssueEvent.OBJECT_KIND, ConfidentialIssueEvent.X_GITLAB_EVENT, "私密议题事件", "已创建、更新、关闭或重新打开机密议题"),
    MERGE_REQUEST(MergeRequestEvent.class, MergeRequestEvent.OBJECT_KIND, MergeRequestEvent.X_GITLAB_EVENT, "合并请求事件", "合并请求事件创建、更新或合并合并请求"),
    JOB(JobEvent.class, JobEvent.OBJECT_KIND, JobEvent.JOB_HOOK_X_GITLAB_EVENT, "作业事件", "作业的状态发生变化"),
    BUILD(BuildEvent.class, BuildEvent.OBJECT_KIND, BuildEvent.JOB_HOOK_X_GITLAB_EVENT, "作业构建事件", ""),
    PIPELINE(PipelineEvent.class, PipelineEvent.OBJECT_KIND, PipelineEvent.X_GITLAB_EVENT, "流水线事件", "流水线的状态变更"),
    WIKI_PAGE(WikiPageEvent.class, WikiPageEvent.OBJECT_KIND, WikiPageEvent.X_GITLAB_EVENT, "Wiki页面事件", "已创建或更新 wiki 页面"),
    DEPLOYMENT(DeploymentEvent.class, DeploymentEvent.OBJECT_KIND, DeploymentEvent.X_GITLAB_EVENT, "部署事件", "部署开始、完成、失败或被取消"),
    RELEASE(ReleaseEvent.class, ReleaseEvent.OBJECT_KIND, ReleaseEvent.X_GITLAB_EVENT, "发布事件", "已创建或更新发布"),

    ;


    private final Class<? extends Event> event;
    private final String value;
    private final String gitlabEvent;
    private final String name;
    private final String description;


}
