package com.voc.api.security.authentication;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;

/**
 * 用户登录成功事件
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/07/04 19:19
 */
@Getter
public class LogInSuccessEvent extends ApplicationEvent {

    private final String msg;

    private final Authentication authentication;

    public LogInSuccessEvent(Authentication authentication, String msg) {
        super(authentication);
        this.authentication = authentication;
        this.msg = msg;
    }
}
