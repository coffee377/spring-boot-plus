package com.voc.api.security.configurer;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/10 11:03
 */
public class CustomLoginPageConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractHttpConfigurer<CustomLoginPageConfigurer<H>, H> {

    @Override
    public void configure(H http) throws Exception {
        DefaultLoginPageGeneratingFilter loginPageGeneratingFilter =
                http.getSharedObject(DefaultLoginPageGeneratingFilter.class);
        if (loginPageGeneratingFilter.isEnabled()) {
            loginPageGeneratingFilter = postProcess(loginPageGeneratingFilter);
            http.addFilter(loginPageGeneratingFilter);
        }

        DefaultLogoutPageGeneratingFilter logoutPageGeneratingFilter
                = http.getSharedObject(DefaultLogoutPageGeneratingFilter.class);
        if (logoutPageGeneratingFilter == null) {
            logoutPageGeneratingFilter = new DefaultLogoutPageGeneratingFilter();
        }
        http.addFilter(logoutPageGeneratingFilter);
    }
}
