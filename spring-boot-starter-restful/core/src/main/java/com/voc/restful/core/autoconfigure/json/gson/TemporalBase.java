package com.voc.restful.core.autoconfigure.json.gson;

import com.google.gson.*;
import com.voc.restful.core.autoconfigure.json.ITemporal;
import com.voc.restful.core.autoconfigure.json.IZoneInfo;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/27 17:25
 */
@Getter
public abstract class TemporalBase<T> implements JsonSerializer<T>, JsonDeserializer<T>, IZoneInfo, ITemporal {

    @Setter
    private String format;

    private final boolean utcTimestamp;

    private final JsonProperties jsonProperties;

    public TemporalBase(JsonProperties jsonProperties) {
        this.jsonProperties = jsonProperties;
        this.format = jsonProperties.getFormat().getInstant();
        this.utcTimestamp = jsonProperties.isUtcTimestamp();
    }

    protected String serialize2String(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    protected Long serialize2Long(LocalDateTime localDateTime) {
        return OffsetDateTime.of(localDateTime, this.getZoneOffset()).toInstant().toEpochMilli();
    }

    public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc, JsonSerializationContext context) {
        if (!this.isUtcTimestamp()) {
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
                    localDateTime = LocalDateTime.of(DEFAULT_INIT_LOCAL_DATE, localTime);
                }
                assert localDateTime != null;
                return localDateTime.toInstant(this.getZoneOffset());
            }
            if (((JsonPrimitive) json).isNumber()) {
                return Instant.ofEpochMilli(json.getAsLong());
            }
        }
        return null;
    }

    public Class<?> getGenericType(){
        ResolvableType resolvableType = ResolvableType.forClass(this.getClass());
        return resolvableType.getSuperType().getGeneric(0).resolve();
    }

}
