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
import java.util.Date;

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
public class DateBean extends BaseBean<Date> {

    public DateBean(JsonProperties jsonProperties) {
        super(jsonProperties);
        this.setFormat(jsonProperties.getFormat().getDate());
    }

    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return super.serialize(localDateTime, typeOfSrc, context);
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Instant instant = deserialize(json);
        if (instant != null) {
            return new Date(instant.toEpochMilli());
        }
        return null;
    }

}
