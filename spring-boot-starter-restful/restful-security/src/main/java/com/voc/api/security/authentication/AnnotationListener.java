package com.voc.api.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 注解方式实现事件（LogInSuccessEvent）监听
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/07/04 19:23
 */
@Slf4j
@Component
public class AnnotationListener {

    @Async
    @EventListener
    public void startSuccess(ApplicationReadyEvent event) {
        log.warn("服务启动成功，加载配置到缓存");
    }

    @Async
    @EventListener
    public void logInSuccess(LogInSuccessEvent logInSuccessEvent) {
        /* 登录成功处理逻辑，更新登录时间及IP 等 */
        Authentication authentication = logInSuccessEvent.getAuthentication();
        if (log.isDebugEnabled()) {
            log.debug("{} {}", authentication.getName(), logInSuccessEvent.getMsg());
        }
//		UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
//		User user = details.getUser();
//		// TODO: 2018/11/13 0013 9:03 登录户处理逻辑
//		/*將用戶信息存的会话Session*/
//		session.setAttribute(CURRENT_USER_ID, user.getId());
//		session.setAttribute(CURRENT_USER_NAME, user.getUserName());
//		// TODO: 2018/11/17 0017 15:33 用户信息变更是需要更新 session 属性值
//		session.setAttribute(LOGIN_USER, user);
//		/*将用户菜单存入缓存或session*/
////		menuService.getAllMenu();
////		menuService.getUserMenuAsList(user.getId(), true);
//		List<Menu> userMenuAsTree = menuService.getUserMenuAsTree(user.getId());
//		session.setAttribute(USER_MENU, userMenuAsTree);
    }


}
