package com.voc.boot.gitlab.webhook.event;

import org.gitlab4j.api.webhook.NoteEvent;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 19:24
 */
public class ConfidentialNoteEvent extends NoteEvent {
    public static final String X_GITLAB_EVENT = "Confidential Note Hook";
    public static final String OBJECT_KIND = "confidential_note";

    @Override
    public String getObjectKind() {
        return OBJECT_KIND;
    }
}
