package com.voc.api.config.json.gson;

import com.google.gson.*;
import com.voc.api.config.json.JsonProperties;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 17:25
 */
@Getter
public abstract class BaseBean<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    @Setter
    private String format;

    private final boolean utcInstant;

    private final ZoneOffset zoneOffset;

    private final JsonProperties jsonProperties;

    public BaseBean(JsonProperties jsonProperties) {
        this.jsonProperties = jsonProperties;
        this.format = jsonProperties.getFormat().getInstant();
        this.utcInstant = jsonProperties.isUtcInstant();
        this.zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
    }

    protected String serialize2String(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    protected Long serialize2Long(LocalDateTime localDateTime) {
        return OffsetDateTime.of(localDateTime, zoneOffset).toInstant().toEpochMilli();
    }

    public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
        if (!this.isUtcInstant()) {
            return new JsonPrimitive(this.serialize2String(localDateTime));
        }
        return new JsonPrimitive(this.serialize2Long(localDateTime));
    }

    public Instant deserialize(JsonElement json) {
        if (json instanceof JsonPrimitive) {
            if (((JsonPrimitive) json).isString()) {
                LocalDateTime localDateTime = null;
                if (format.equals(this.getJsonProperties().getFormat().getInstant())
                        || format.equals(this.getJsonProperties().getFormat().getLocalDateTime())) {
                    localDateTime = LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern(format));
                } else if (format.equals(this.getJsonProperties().getFormat().getLocalDate())) {
                    LocalDate localDate = LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern(format));
                    localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
                } else if (format.equals(this.getJsonProperties().getFormat().getLocalTime())) {
                    LocalTime localTime = LocalTime.parse(json.getAsString(), DateTimeFormatter.ofPattern(format));
                    localDateTime = LocalDateTime.of(LocalDate.of(1970, 1, 1), localTime);
                }
                assert localDateTime != null;
                return localDateTime.toInstant(zoneOffset);
            }
            if (((JsonPrimitive) json).isNumber()) {
                return Instant.ofEpochMilli(json.getAsLong());
            }
        }
        return null;
    }

}
