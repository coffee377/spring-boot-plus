package com.voc.security.authentication;

import com.voc.restful.core.response.Result;
import com.voc.security.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 17:58
 */
@Slf4j
public class RestfulAuthenticationSuccessHandler implements AuthenticationSuccessHandler,
        ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    //    @Resource
//    private OAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        publisher.publishEvent(new LoginSuccessEvent(authentication, "用户登录成功"));
        Object result = authentication;
//        if (authentication instanceof OAuth2AuthenticationToken) {
//            String registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
//            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(registrationId)
//                    .principal(authentication)
//                    .attributes(attrs -> {
//                        attrs.put(HttpServletRequest.class.getName(), request);
//                        attrs.put(HttpServletResponse.class.getName(), response);
//                    })
//                    .build();
//            OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
//            assert authorizedClient != null;
//            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
//            OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();
//            result = accessToken;
//        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            String name = authentication.getName();
//        }

        String res = Result.success(result).toString();
        if (log.isDebugEnabled()) {
            log.debug("响应 JSON 数据为：{}", res);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(res);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    private String genToken(Authentication authentication) {
        return "123";
    }

}
