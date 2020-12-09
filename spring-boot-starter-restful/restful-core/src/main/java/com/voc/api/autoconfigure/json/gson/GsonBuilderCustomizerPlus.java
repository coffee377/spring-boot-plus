package com.voc.api.autoconfigure.json.gson;

import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 12:19
 */
@Slf4j
public class GsonBuilderCustomizerPlus implements GsonBuilderCustomizer, Ordered {

    private final ApplicationContext applicationContext;

    public GsonBuilderCustomizerPlus(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void customize(GsonBuilder builder) {
        Map<String, TemporalBase> temporal = applicationContext.getBeansOfType(TemporalBase.class);
        temporal.values().forEach(temporalBase -> {
            Class type = temporalBase.getGenericType();
            log.debug("{} => {} ", type, temporalBase);
            builder.registerTypeAdapter(type, temporalBase);
        });
//        builder.registerTypeAdapter(Date.class,)
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
