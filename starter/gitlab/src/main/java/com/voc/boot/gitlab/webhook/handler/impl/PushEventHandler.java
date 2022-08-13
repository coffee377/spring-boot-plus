package com.voc.boot.gitlab.webhook.handler.impl;

import com.voc.boot.gitlab.webhook.HookType;
import com.voc.boot.gitlab.webhook.handler.AbstractWebHookHandler;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.webhook.PushEvent;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 16:20
 */
@Slf4j
public class PushEventHandler extends AbstractWebHookHandler<PushEvent, String> {
    @Override
    protected String handle(PushEvent event) {
        return null;
    }

    @Override
    protected boolean support(HookType type, PushEvent event) {
        return false;
    }
}
