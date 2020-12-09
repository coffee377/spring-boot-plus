package com.voc.api.autoconfigure.json.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.voc.api.autoconfigure.json.JsonProperties;
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
public class InstantBean extends TemporalBase<Instant> {

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
