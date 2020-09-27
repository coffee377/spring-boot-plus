package com.voc.api.config.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.api.config.json.JsonFormat;
import com.voc.api.config.json.JsonProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * jackson 日期序列化实现类
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/24 12:14
 */
@Getter
@Setter
public class DateJsonSerializer extends JsonSerializer<Object> {

    private JsonProperties jsonProperties;

    public DateJsonSerializer(JsonProperties jsonProperties) {
        this.jsonProperties = jsonProperties;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        JsonFormat format = jsonProperties.getFormat();
        boolean utcInstant = jsonProperties.isUtcInstant();
        Object result = null;
        if (value instanceof Instant) {
            result = writeInstant((Instant) value, utcInstant, format.getInstant());
        } else if (value instanceof LocalDateTime) {
            result = writeLocalDateTime((LocalDateTime) value, utcInstant, format.getLocalDateTime());
        } else if (value instanceof LocalDate) {
            result = writeLocalDate((LocalDate) value, utcInstant, format.getLocalDate());
        } else if (value instanceof LocalTime) {
            result = writeLocalTime((LocalTime) value, utcInstant, format.getLocalTime());
        } else if (value instanceof Date) {
            result = writeDate((Date) value, utcInstant, format.getDate());
        }

        if (result != null) {
            gen.writeObject(result);
        }

    }

    private Object writeLocalDateTime(LocalDateTime localDateTime, boolean utcInstant, String format) {
        if (!utcInstant) {
            return localDateTime.format(DateTimeFormatter.ofPattern(format));
        }
        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
        return OffsetDateTime.of(localDateTime, zoneOffset).toInstant().toEpochMilli();
    }

    private Object writeInstant(Instant instant, boolean utcInstant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return writeLocalDateTime(localDateTime, utcInstant, format);
    }

    private Object writeLocalDate(LocalDate localDate, boolean utcInstant, String format) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        return writeLocalDateTime(localDateTime, utcInstant, format);
    }

    private Object writeLocalTime(LocalTime localTime, boolean utcInstant, String format) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(1970, 1, 1), localTime);
        return writeLocalDateTime(localDateTime, utcInstant, format);
    }

    private Object writeDate(Date date, boolean utcInstant, String format) {
        return writeInstant(date.toInstant(), utcInstant, format);
    }
}
