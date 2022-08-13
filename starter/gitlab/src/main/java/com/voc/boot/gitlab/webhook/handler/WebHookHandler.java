package com.voc.boot.gitlab.webhook.handler;

import com.voc.boot.gitlab.webhook.HookType;
import org.gitlab4j.api.webhook.Event;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/07 22:42
 */
public interface WebHookHandler<D extends Event, R> {

    /**
     * 处理webhook事件
     *
     * @param event webhook 事件数据
     * @return 响应
     */
    R handle(HookType type, D event);

}
