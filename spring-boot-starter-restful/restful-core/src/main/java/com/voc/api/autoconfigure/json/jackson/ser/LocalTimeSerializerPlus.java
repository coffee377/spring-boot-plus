package com.voc.api.autoconfigure.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.api.autoconfigure.json.JsonProperties;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 11:35
 */
@Component
public class LocalTimeSerializerPlus extends BaseTemporalSerializer<LocalTime> {

    public LocalTimeSerializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
        setFormat(jsonProperties.getFormat().getLocalTime());
    }

    @Override
    protected Long serialize2Timestamp(LocalTime value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2Timestamp(this.getLocalDateTime(value), this.getZoneOffset());
    }

    @Override
    protected String serialize2String(LocalTime value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2String(this.getLocalDateTime(value), this.getFormat());
    }
}
