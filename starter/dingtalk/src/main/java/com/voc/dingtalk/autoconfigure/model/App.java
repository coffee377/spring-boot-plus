package com.voc.dingtalk.autoconfigure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 16:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class App extends Base {
    /**
     * 是否为主应用
     */
    private boolean primary;

    /**
     * 事件订阅配置
     */
    private EventSubscription eventSubscription;

}
