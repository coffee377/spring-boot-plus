package com.voc.restful.core.autoconfigure.json.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import com.voc.restful.core.autoconfigure.json.JsonType;
import com.voc.restful.core.autoconfigure.json.annotation.Temporal;

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
@Temporal(JsonType.GSON)
public class DateBean extends TemporalBase<Date> {

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
