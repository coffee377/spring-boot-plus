package com.voc.api.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支持异步请求
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2017/12/27 17:50
 */
//@Component
@Slf4j
public class RestfulAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
//        if (AjaxUtils.isAjax(request) && AjaxUtils.isJson(request)) {
//            String datas = "";
//            try {
//                datas = IOUtils.toString(request.getInputStream(), "UTF-8");
//                if (log.isDebugEnabled()) {
//                    log.debug("异步请求数据是：{}", datas);
//                }
//            } catch (IOException e) {
//                if (log.isErrorEnabled()) {
//                    log.error(e.getMessage());
//                }
//            }
//            Certification form = JSON.parseObject(datas, Certification.class);
//            UsernamePasswordAuthenticationToken authRequest =
//                    new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
//            setDetails(request, authRequest);
//            return this.getAuthenticationManager().authenticate(authRequest);
//        } else {
        return super.attemptAuthentication(request, response);
//        }
    }


}
