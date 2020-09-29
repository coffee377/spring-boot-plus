package com.voc.api.autoconfigure.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 15:17
 */
public class DefaultJson implements IJson {

    private final ApplicationContext applicationContext;

    private final Environment environment;

    public DefaultJson(ApplicationContext applicationContext, Environment environment) {
        this.applicationContext = applicationContext;
        this.environment = environment;
    }

    @Override
    public String serializer(Object obj) throws Exception {
        switch (getJsonType()) {
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
    public <T> T deserializer(String jsonSting, Class<T> targetType) throws JsonProcessingException {
        switch (getJsonType()) {
            case GSON:
                Gson gson = applicationContext.getBean(Gson.class);
                return gson.fromJson(jsonSting, targetType);
            case FASTJSON:
            case JSONB:
            case JACKSON:
            default:
                ObjectMapper mapper = applicationContext.getBean(ObjectMapper.class);
                return mapper.readValue(jsonSting, targetType);
        }
    }


    protected JsonType getJsonType() {
        return environment.getProperty(JsonConstants.API_JSON_TYPE_PREFIX, JsonType.class, JsonType.JACKSON);
    }
}
