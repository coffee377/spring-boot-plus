package com.voc.dingtalk.cache;

import com.voc.dingtalk.properties.App;
import com.voc.dingtalk.service.IDingTalkService;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/10 22:40
 */
@Component("appKeyGenerator")
public class AppKeyGenerator implements KeyGenerator {

    @Resource
    private IDingTalkService dingTalkService;

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        App app;
        if (objects.length == 1) {
            app = dingTalkService.getAppByName(objects[0].toString());
        } else {
            app = dingTalkService.getAppById(objects[0].toString());
        }
        if (app != null) {
            return app.getName();
        }
        return "";
    }
}
