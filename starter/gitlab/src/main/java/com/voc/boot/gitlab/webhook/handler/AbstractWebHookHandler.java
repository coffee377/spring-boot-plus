package com.voc.boot.gitlab.webhook.handler;

import com.voc.boot.gitlab.webhook.HookType;
import org.gitlab4j.api.webhook.Event;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 20:32
 */
public abstract class AbstractWebHookHandler<D extends Event, R> implements WebHookHandler<D, R> {

    @Override
    public R handle(HookType type, D event) {
        if (support(type, event)) {
            return handle(event);
        }
        return null;
    }


    /**
     * 处理webhook事件
     *
     * @param event webhook 事件数据
     * @return 响应
     */
    protected abstract R handle(D event);

    /**
     * 是否支持时间事件处理
     *
     * @param type  HookType
     * @param event D
     * @return boolean
     */
    protected abstract boolean support(HookType type, D event);
}
