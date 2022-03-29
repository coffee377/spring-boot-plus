package com.voc.restful.security.core.authentication;

import com.voc.restful.core.third.ThirdAppService;
import com.voc.restful.security.core.authentication.qrcode.QRAuthenticationProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.Collection;

/**
 * 配置添加扫码认证处理器
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/25 21:59
 */
public class QRAuthenticationProviderConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<QRAuthenticationProviderConfigurer<H>, H> {
    private QRAuthenticationProvider authenticationProvider;
    private ApplicationContext context;

    @Override
    public void init(H http) throws Exception {
        context = http.getSharedObject(ApplicationContext.class);
        authenticationProvider = getBeanOrNull(QRAuthenticationProvider.class);
    }

    @Override
    public void configure(H http) throws Exception {
        if (authenticationProvider == null) {
            Collection<ThirdAppService> thirdAppServices = context.getBeansOfType(ThirdAppService.class).values();
            authenticationProvider = new QRAuthenticationProvider(thirdAppServices);
        }
        http.setSharedObject(QRAuthenticationProvider.class, authenticationProvider);

        authenticationProvider.afterPropertiesSet();

        http.authenticationProvider(authenticationProvider);
    }

    private <T> T getBeanOrNull(Class<T> type) {
        String[] beanNames = context.getBeanNamesForType(type);
        if (beanNames.length != 1) {
            return null;
        }

        return context.getBean(beanNames[0], type);
    }

}
