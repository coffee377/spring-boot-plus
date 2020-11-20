package com.voc.api.autoconfigure.json.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.voc.api.autoconfigure.json.JsonProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.*;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 17:25
 */
@Component
public class LocalDateBean extends TemporalBase<LocalDate> {

    public LocalDateBean(JsonProperties jsonProperties) {
        super(jsonProperties);
        this.setFormat(jsonProperties.getFormat().getLocalDate());
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type typeOfSrc, JsonSerializationContext context) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        return super.serialize(localDateTime, typeOfSrc, context);
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Instant instant = deserialize(json);
        if (instant != null) {
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

}
