package com.voc.api.config.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期序列化实现类
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
        if (value instanceof LocalDateTime) {
            gen.writeObject(((LocalDateTime) value).format(DateTimeFormatter.ofPattern(format.getLocalDateTime())));
        } else if (value instanceof LocalDate) {
            gen.writeObject(((LocalDate) value).format(DateTimeFormatter.ofPattern(format.getLocalDate())));
        } else if (value instanceof LocalTime) {
            gen.writeObject(((LocalTime) value).format(DateTimeFormatter.ofPattern(format.getLocalTime())));
        } else if (value instanceof Date) {
            SimpleDateFormat formatter = new SimpleDateFormat(format.getDate());
            gen.writeObject(formatter.format(value));
        }
    }
}
