package com.voc.security.core.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

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

    private OAuth2AccessTokenResponse tokenResult;

    public LoginSuccessEvent(String msg,Authentication authentication) {
        this(msg, authentication, null);
    }

    public LoginSuccessEvent(String msg, Authentication authentication, OAuth2AccessTokenResponse tokenResult) {
        super(authentication);
        this.msg = msg;
        this.authentication = authentication;
        this.tokenResult = tokenResult;
    }

    public void setTokenResult(OAuth2AccessTokenResponse tokenResult) {
        this.tokenResult = tokenResult;
    }
}
