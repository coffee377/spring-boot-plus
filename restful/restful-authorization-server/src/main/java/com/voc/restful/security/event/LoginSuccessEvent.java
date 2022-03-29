package com.voc.restful.security.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;

/**
 * 用户登录成功事件
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/07/04 19:19
 */
@Getter
public class LoginSuccessEvent extends ApplicationEvent {

    private final String msg;

    private final Authentication authentication;

    public LoginSuccessEvent(Authentication authentication, String msg) {
        super(authentication);
        this.authentication = authentication;
        this.msg = msg;
    }
}
