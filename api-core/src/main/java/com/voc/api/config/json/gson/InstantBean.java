package com.voc.api.config.json.gson;

import com.google.gson.*;
import com.voc.api.config.json.JsonProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 17:25
 */
@Component
@ConditionalOnClass(Gson.class)
@ConditionalOnProperty(
        prefix = "api.json",
        name = "type",
        havingValue = "gson"
)
public class InstantBean extends BaseBean<Instant> {

    public InstantBean(JsonProperties jsonProperties) {
        super(jsonProperties);
    }

    @Override
    public JsonElement serialize(Instant instant, Type typeOfSrc, JsonSerializationContext context) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return super.serialize(localDateTime, typeOfSrc, context);
    }

    @Override
    public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return deserialize(json);
    }
}
