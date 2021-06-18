package com.voc.security.authentication;

import com.voc.restful.core.response.Result;
import com.voc.security.event.LoginSuccessEvent;
import com.voc.security.token.TokenGenerator;
import com.voc.security.token.TokenProperties;
import com.voc.security.token.TokenResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 17:58
 */
@Slf4j
public class RestfulAuthenticationSuccessHandler implements AuthenticationSuccessHandler, ApplicationContextAware,
        ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    private ApplicationContext applicationContext;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        publisher.publishEvent(new LoginSuccessEvent(authentication, "用户登录成功"));
        TokenResult tokenResult = this.genToken(authentication);
        String res = Result.success(tokenResult).toString();
        if (log.isDebugEnabled()) {
            log.debug("响应 JSON 数据为：{}", res);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(res);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    private TokenResult genToken(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        UserDetails userDetails;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        } else {
            String username = authentication.getName();
            UserDetailsService userDetailsService = applicationContext.getBean(UserDetailsService.class);
            userDetails = userDetailsService.loadUserByUsername(username);
        }
        TokenGenerator tokenGenerator = applicationContext.getBean(TokenGenerator.class);
        TokenProperties tokenProperties = applicationContext.getBean(TokenProperties.class);
        return tokenGenerator.create(Instant.now(), userDetails, !tokenProperties.isDualToken());
    }

}
