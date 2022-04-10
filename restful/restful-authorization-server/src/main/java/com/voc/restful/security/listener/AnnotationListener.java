package com.voc.restful.security.listener;

import com.voc.restful.security.core.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;

/**
 * 注解方式实现事件（LogInSuccessEvent）监听
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/07/04 19:23
 */
@Slf4j
public class AnnotationListener {

    @Async
    @EventListener
    public void startSuccess(ApplicationReadyEvent event) {
        log.warn("服务启动成功，加载配置到缓存");
    }

    @Async
    @EventListener
    public void loginSuccess(LoginSuccessEvent loginSuccessEvent) {
        /* 登录成功处理逻辑，更新登录时间及IP 等 */
        Authentication authentication = loginSuccessEvent.getAuthentication();
        if (log.isDebugEnabled()) {
            log.debug("{} {}", authentication.getName(), loginSuccessEvent.getMsg());
        }
    }

    @Async
    @EventListener
    public void logoutSuccess(LogoutSuccessEvent logoutSuccessEvent) {
        log.debug("用户退出成功");
    }


}
