package com.voc.security.core.authentication.filter;

import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉授权码参数转换过滤器（钉钉授权码参数不符合 OAuth2 规范）
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/03 12:26
 * @see org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter
 */
public class DingTalkAuthCodeConvertFilter extends OncePerRequestFilter {

    public static final String AUTH_CODE = "authCode";
    private RequestMatcher requestMatcher = new AndRequestMatcher(
            new AntPathRequestMatcher("/login/oauth2/code/*")
    );


    public void setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!this.requestMatcher.matches(request) && !hasAuthCodeParameter(request)) {
            if (logger.isTraceEnabled()) {
                logger.trace("Did not match request to " + this.requestMatcher);
            }
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequestWrapper servletRequestWrapper = new HttpServletRequestWrapper(request) {
            @Override
            public Map<String, String[]> getParameterMap() {
                HashMap<String, String[]> hashMap = new HashMap<>(2);
                super.getParameterMap().forEach((key, values) -> {
                    if (AUTH_CODE.equals(key)) {
                        hashMap.put("code", values);
                    } else {
                        hashMap.put(key, values);
                    }
                });

                return Collections.unmodifiableMap(hashMap);
            }
        };
        filterChain.doFilter(servletRequestWrapper, response);
    }

    /**
     * 是否含有 AuthCode 参数
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    private boolean hasAuthCodeParameter(HttpServletRequest request) {
        return request.getParameterMap().containsKey(AUTH_CODE);
    }
}
