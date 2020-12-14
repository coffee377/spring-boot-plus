package com.voc.restful.core.autoconfigure.json.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 17:25
 */
@Component
public class LocalTimeBean extends TemporalBase<LocalTime> {

    public LocalTimeBean(JsonProperties jsonProperties) {
        super(jsonProperties);
        this.setFormat(jsonProperties.getFormat().getLocalTime());
    }

    @Override
    public JsonElement serialize(LocalTime localTime, Type typeOfSrc, JsonSerializationContext context) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(1970, 1, 1), localTime);
        return super.serialize(localDateTime, typeOfSrc, context);
    }

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Instant instant = deserialize(json);
        if (instant != null) {
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
        }
        return null;
    }


}
