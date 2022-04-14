package com.voc.restful.security.core.event;

import com.voc.restful.security.core.authentication.token.model.TokenResult;
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

    private TokenResult tokenResult;

    public LoginSuccessEvent(String msg,Authentication authentication) {
        this(msg, authentication, null);
    }

    public LoginSuccessEvent(String msg, Authentication authentication, TokenResult tokenResult) {
        super(authentication);
        this.msg = msg;
        this.authentication = authentication;
        this.tokenResult = tokenResult;
    }

    public void setTokenResult(TokenResult tokenResult) {
        this.tokenResult = tokenResult;
    }
}
