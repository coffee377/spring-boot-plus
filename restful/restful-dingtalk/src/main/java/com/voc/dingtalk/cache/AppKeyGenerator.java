package com.voc.dingtalk.cache;

import com.voc.dingtalk.annotation.PrimaryApp;
import com.voc.dingtalk.autoconfigure.App;
import com.voc.dingtalk.service.IAppService;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

/**
 * 应用缓存根据应用名称进行缓存
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 22:40
 */
@Component("appKeyGenerator")
public class AppKeyGenerator implements KeyGenerator {

    private final IAppService appService;

    public AppKeyGenerator(IAppService appService) {
        this.appService = appService;
    }

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        if (!ObjectUtils.isEmpty(objects)) {
            App app = appService.getByIdOrName(objects[0].toString());
            if (app != null) {
                return app.getName();
            }
        }
        if (method.isAnnotationPresent(PrimaryApp.class)) {
            return appService.getPrimaryApp().getName();
        }
        return "";
    }
}
