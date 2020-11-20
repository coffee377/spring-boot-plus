package com.voc.api.autoconfigure.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.api.autoconfigure.json.JsonProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 09:26
 */
@Component
public class LocalDateSerializerPlus extends BaseTemporalSerializer<LocalDate> {

    public LocalDateSerializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
        setFormat(jsonProperties.getFormat().getLocalDate());
    }

    @Override
    protected Long serialize2Timestamp(LocalDate value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2Timestamp(this.getLocalDateTime(value), this.getZoneOffset());
    }

    @Override
    protected String serialize2String(LocalDate value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2String(this.getLocalDateTime(value), this.getFormat());
    }

}
