package com.voc.dingtalk.cache;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/11 13:51
 */
public class CacheInitListener implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * 重启应用完成后，清除 APP 信息缓存
     *
     * @param event ApplicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        Map<String, CacheManager> beansOfType = applicationContext.getBeansOfType(CacheManager.class);
        beansOfType.values().forEach(cacheManager -> {
            Cache cache = cacheManager.getCache(DingTalkCache.APP_INFO);
            if (cache != null) {
                cache.clear();
            }
        });
    }
}
