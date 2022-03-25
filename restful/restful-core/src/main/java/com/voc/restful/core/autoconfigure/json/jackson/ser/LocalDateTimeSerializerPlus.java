package com.voc.restful.core.autoconfigure.json.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.voc.restful.core.autoconfigure.json.JsonProperties;
import com.voc.restful.core.autoconfigure.json.annotation.Temporal;

import java.time.LocalDateTime;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/09/28 09:26
 */
@Temporal
public class LocalDateTimeSerializerPlus extends BaseTemporalSerializer<LocalDateTime> {

    public LocalDateTimeSerializerPlus(JsonProperties jsonProperties) {
        super(jsonProperties);
        setFormat(jsonProperties.getFormat().getLocalDateTime());
    }

    @Override
    protected Long serialize2Timestamp(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2Timestamp(value, this.getZoneOffset());
    }

    @Override
    protected String serialize2String(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) {
        return this.serialize2String(value, this.getFormat());
    }

}
