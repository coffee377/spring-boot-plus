package com.voc.api.security.authentication;

import com.voc.api.Constants;
import com.voc.api.autoconfigure.LoginProperties;
import com.voc.api.response.BaseBizError;
import com.voc.api.response.Result;
import com.voc.api.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/03/19 14:11
 */
@Slf4j
@Component
public class RestfulAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final PortMapper portMapper = new PortMapperImpl();

    private final PortResolver portResolver = new PortResolverImpl();

    private final LoginProperties loginProperties;

    public RestfulAuthenticationEntryPoint(LoginProperties loginProperties) {
        super(StringUtils.isEmpty(loginProperties.getPage()) ? Constants.DEFAULT_LOGIN_PAGE_URL : loginProperties.getPage());
        this.loginProperties = loginProperties;
    }

    /**
     * 认证异常处理
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param e        AuthenticationException
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (log.isErrorEnabled()) {
            log.info("访问资源 {} 需要用户身份认证", request.getRequestURL().toString());
        }

        if (RequestUtil.isRestfulRequest(request)) {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Result failure = Result.failure(BaseBizError.UNAUTHORIZED);
            if (e instanceof InvalidBearerTokenException) {
                failure = Result.failure(BaseBizError.INVALID_BEARER_TOKEN);
            } else if (e instanceof UsernameNotFoundException) {
                failure = Result.failure(BaseBizError.USERNAME_NOT_FOUND);
            } else if (e instanceof BadCredentialsException) {
                failure = Result.failure(BaseBizError.BAD_CREDENTIALS);
            }
            if (log.isDebugEnabled()) {
                log.debug("响应 JSON 数据为：{}", failure.toString());
            }
            response.getWriter().write(failure.toString());
        } else {
            StringBuilder redirectUrl = new StringBuilder();
            redirectUrl.append(buildRedirectUrl(request, response, e, true));
            redirectUrl.append("?redirect_url=");
            redirectUrl.append(buildRedirectUrl(request, response, e, false));
            redirectStrategy.sendRedirect(request, response, redirectUrl.toString());
        }

    }

    protected String buildRedirectUrl(HttpServletRequest request,
                                      HttpServletResponse response,
                                      AuthenticationException authException,
                                      boolean loginPage) {
        String url = Constants.DEFAULT_LOGIN_PAGE_URL;
        if (!StringUtils.isEmpty(loginProperties.getPage())) {
            url = loginProperties.getPage();
        }

        if (!loginPage) {
            url = request.getContextPath() + request.getServletPath();
        }

        if (UrlUtils.isAbsoluteUrl(url)) {
            return url;
        }

        int serverPort = portResolver.getServerPort(request);
        String scheme = request.getScheme();

        RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();

        urlBuilder.setScheme(scheme);
        urlBuilder.setServerName(request.getServerName());
        urlBuilder.setPort(serverPort);
        urlBuilder.setContextPath(request.getContextPath());
        urlBuilder.setPathInfo(url);

        return urlBuilder.getUrl();
    }

}
