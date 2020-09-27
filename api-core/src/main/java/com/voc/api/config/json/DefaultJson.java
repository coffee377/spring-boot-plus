package com.voc.api.config.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 15:17
 */
@Component("json")
public class DefaultJson implements IJson, ApplicationContextAware, EnvironmentAware {

    private ApplicationContext applicationContext;

    private Environment environment;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String serializer(Object obj) throws Exception {
        JsonType type = environment.getProperty(JsonConstants.JSON_TYPE_PREFIX, JsonType.class, JsonType.JACKSON);
        switch (type) {
            case GSON:
                Gson gson = applicationContext.getBean(Gson.class);
                return gson.toJson(obj);
            case FASTJSON:
            case JSONB:
            case JACKSON:
            default:
                ObjectMapper mapper = applicationContext.getBean(ObjectMapper.class);
                return mapper.writeValueAsString(obj);
        }
    }

    @Override
    public <T> T deserializer(String jsonSting, Class<T> targetType) {
        return null;
    }

}
