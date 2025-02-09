package com.voc.restful.core.autoconfigure.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.voc.restful.core.autoconfigure.json.exception.JsonDeserializeException;
import com.voc.restful.core.autoconfigure.json.exception.JsonSerializeException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;

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
    public String serializer(Object obj) throws JsonSerializeException {
        switch (getJsonType()) {
            case GSON:
                Gson gson = applicationContext.getBean(Gson.class);
                return gson.toJson(obj);
            case JACKSON:
            default:
                ObjectMapper mapper = applicationContext.getBean(ObjectMapper.class);
                try {
                    return mapper.writeValueAsString(obj);
                } catch (JsonProcessingException e) {
                    throw new JsonSerializeException();
                }
        }
    }

    @Override
    public <T> T deserializer(String jsonSting, Class<T> targetType) throws JsonDeserializeException {
        switch (getJsonType()) {
            case GSON:
                Gson gson = applicationContext.getBean(Gson.class);
                return gson.fromJson(jsonSting, targetType);
            case JACKSON:
            default:
                ObjectMapper mapper = applicationContext.getBean(ObjectMapper.class);
                try {
                    return mapper.readValue(jsonSting, targetType);
                } catch (IOException e) {
                    throw new JsonDeserializeException();
                }
        }
    }

    @Override
    public <T> T deserializer(InputStream inputStream, Class<T> targetType) throws JsonDeserializeException {
        ObjectMapper mapper = applicationContext.getBean(ObjectMapper.class);
        try {
            return mapper.readValue(inputStream, targetType);
        } catch (IOException e) {
            throw new JsonDeserializeException();
        }
    }

    protected JsonType getJsonType() {
        return environment.getProperty(JsonConstants.API_JSON_TYPE_PREFIX, JsonType.class, JsonType.JACKSON);
    }
}
